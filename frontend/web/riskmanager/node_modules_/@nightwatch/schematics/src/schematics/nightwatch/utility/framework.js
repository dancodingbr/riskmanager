"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.TYPESCRIPT = exports.REACT_NATIVE = exports.REACT_TS = exports.REACT = exports.ANGULAR = void 0;
const schematics_1 = require("@angular-devkit/schematics");
exports.ANGULAR = 'angular';
exports.REACT = 'react';
exports.REACT_TS = 'react-ts';
exports.REACT_NATIVE = 'react-native';
exports.TYPESCRIPT = 'typescript';
function getFramework(host) {
    let possibleFiles = ['/package.json'];
    const filePath = possibleFiles.filter((path) => host.exists(path))[0];
    const configBuffer = host.read(filePath);
    if (configBuffer === null) {
        throw new schematics_1.SchematicsException(`couldn't find ${filePath}`);
    }
    else {
        const content = JSON.parse(configBuffer.toString());
        if (content.dependencies['@angular/core']) {
            return exports.ANGULAR;
        }
        else if (content.dependencies['react']) {
            if (content.dependencies['react-native']) {
                return exports.REACT_NATIVE;
            }
            if (content.dependencies['typescript']) {
                return exports.REACT_TS;
            }
            return exports.REACT;
        }
        else if ((content.dependencies['typescript'] || content.devDependencies['typescript']) &&
            !content.dependencies['react']) {
            return exports.TYPESCRIPT;
        }
        else {
            throw new schematics_1.SchematicsException('No supported frameworks found in your package.json!');
        }
    }
}
exports.default = getFramework;
//# sourceMappingURL=framework.js.map