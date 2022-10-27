"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.Config = exports.pkgJson = exports.NodeDependencyType = void 0;
var NodeDependencyType;
(function (NodeDependencyType) {
    NodeDependencyType["Default"] = "dependencies";
    NodeDependencyType["Dev"] = "devDependencies";
    NodeDependencyType["Peer"] = "peerDependencies";
    NodeDependencyType["Optional"] = "optionalDependencies";
})(NodeDependencyType = exports.NodeDependencyType || (exports.NodeDependencyType = {}));
var pkgJson;
(function (pkgJson) {
    pkgJson["Path"] = "/package.json";
})(pkgJson = exports.pkgJson || (exports.pkgJson = {}));
var Config;
(function (Config) {
    Config["PackageJsonPath"] = "package.json";
    Config[Config["JsonIndentLevel"] = 4] = "JsonIndentLevel";
})(Config = exports.Config || (exports.Config = {}));
//# sourceMappingURL=enums.js.map