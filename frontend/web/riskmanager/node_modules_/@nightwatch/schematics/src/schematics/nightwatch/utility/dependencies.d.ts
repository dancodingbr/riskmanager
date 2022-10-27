import { Tree } from '@angular-devkit/schematics';
import { DeleteNodeDependency, NodeDependency } from '../interfaces';
export declare function getPackageJsonDependency(tree: Tree, name: string): NodeDependency | null;
export declare function addPackageJsonDependency(tree: Tree, dependency: NodeDependency): void;
export declare function removePackageJsonDependency(tree: Tree, dependency: DeleteNodeDependency): void;
