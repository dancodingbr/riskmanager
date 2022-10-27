import { Rule, SchematicContext, Tree } from '@angular-devkit/schematics';
import { SchematicsOptions } from './interfaces';
export default function (_options: SchematicsOptions): Rule;
export declare function addNightwatchTestsScriptToPackageJson(options: SchematicsOptions): (tree: Tree, context: SchematicContext) => import("@angular-devkit/schematics/src/tree/interface").Tree;
export declare const removeE2ELinting: (tree: Tree, angularJsonVal: any, project: string) => void;
export declare const removeE2EConfig: (tree: Tree, angularJsonVal: any, project: string) => void;
export declare const addNightwatchTsConfig: (tree: Tree, angularJsonVal: any, projectName: string) => void;
