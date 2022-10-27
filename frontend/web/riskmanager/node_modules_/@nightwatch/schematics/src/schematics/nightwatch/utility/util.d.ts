import { SchematicContext, Tree } from '@angular-devkit/schematics';
import { NodePackage } from '../interfaces';
export declare function getAngularVersion(tree: Tree): number;
export declare function addPropertyToPackageJson(tree: Tree, context: SchematicContext, propertyName: string, propertyValue: {
    [key: string]: any;
}): void;
/**
 * Attempt to retrieve the latest package version from NPM
 * Return an optional "latest" version in case of error
 * @param packageName
 */
export declare function getLatestNodeVersion(packageName: string): Promise<NodePackage>;
