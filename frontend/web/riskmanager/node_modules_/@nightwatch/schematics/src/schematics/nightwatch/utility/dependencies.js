"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.removePackageJsonDependency = exports.addPackageJsonDependency = exports.getPackageJsonDependency = void 0;
const enums_1 = require("../enums");
const jsonFile_1 = require("./jsonFile");
const jsonc_parser_1 = require("jsonc-parser");
const json_utils_1 = require("./json-utils");
function getPackageJsonDependency(tree, name) {
    const packageJson = new jsonFile_1.JSONFile(tree, enums_1.pkgJson.Path);
    let dep = null;
    [
        enums_1.NodeDependencyType.Default,
        enums_1.NodeDependencyType.Dev,
        enums_1.NodeDependencyType.Optional,
        enums_1.NodeDependencyType.Peer,
    ].forEach((depType) => {
        if (dep !== null) {
            return;
        }
        const depsNode = packageJson.get([depType]);
        if ((depsNode === null || depsNode === void 0 ? void 0 : depsNode.type) === 'object') {
            const depNode = (0, jsonc_parser_1.findNodeAtLocation)(depsNode, [name]);
            if (depNode && depNode.type === 'string') {
                const version = depNode.value;
                dep = {
                    type: depType,
                    name: name,
                    version: version,
                };
            }
        }
    });
    return dep;
}
exports.getPackageJsonDependency = getPackageJsonDependency;
function addPackageJsonDependency(tree, dependency) {
    const packageJsonAst = new jsonFile_1.JSONFile(tree, enums_1.pkgJson.Path);
    const depsNode = packageJsonAst.get([dependency.type]);
    const recorder = tree.beginUpdate(enums_1.pkgJson.Path);
    if (!depsNode) {
        // Haven't found the dependencies key, add it to the root of the package.json.
        (0, json_utils_1.insertPropertyInAstObjectInOrder)(recorder, packageJsonAst.JsonAst, dependency.type, { [dependency.name]: dependency.version }, 2);
    }
    else if (depsNode && depsNode.type === 'object') {
        // check if package already added
        const depNode = (0, jsonc_parser_1.findNodeAtLocation)(depsNode, [dependency.name]);
        if (!depNode) {
            // Package not found, add it.
            (0, json_utils_1.insertPropertyInAstObjectInOrder)(recorder, depsNode, dependency.name, dependency.version, 4);
        }
        else if (dependency.overwrite) {
            // Package found, update version if overwrite.
            if (depNode.colonOffset === undefined) {
                throw new Error('Invalid package.json. Was expecting an object');
            }
            recorder.remove(depNode.offset + depNode.colonOffset, depNode.offset + depNode.length);
            recorder.insertRight(depNode.offset + depNode.colonOffset, JSON.stringify(dependency.version));
        }
    }
    tree.commitUpdate(recorder);
}
exports.addPackageJsonDependency = addPackageJsonDependency;
function removePackageJsonDependency(tree, dependency) {
    var _a;
    const packageJsonAst = new jsonFile_1.JSONFile(tree, enums_1.pkgJson.Path);
    const depsNode = packageJsonAst.get([dependency.type]);
    const recorder = tree.beginUpdate(enums_1.pkgJson.Path);
    if (!depsNode || !depsNode.children) {
        return;
    }
    const depNode = (_a = (0, jsonc_parser_1.findNodeAtLocation)(depsNode, [dependency.name])) === null || _a === void 0 ? void 0 : _a.parent;
    if (!depNode)
        return;
    let start = depNode.offset, length = depNode.length;
    let pos = depsNode.children.indexOf(depNode);
    // Previous property present
    if (pos - 1 > -1) {
        const prevNode = depsNode.children[pos - 1];
        start = prevNode.offset + prevNode.length + 1;
        length = depNode.offset + depNode.length - start;
    }
    // Next property present
    if (pos < depsNode.children.length - 1) {
        length++; // remove comma
    }
    recorder.remove(start, length);
    tree.commitUpdate(recorder);
}
exports.removePackageJsonDependency = removePackageJsonDependency;
//# sourceMappingURL=dependencies.js.map