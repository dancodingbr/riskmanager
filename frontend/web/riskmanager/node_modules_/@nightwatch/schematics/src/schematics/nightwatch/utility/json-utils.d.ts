import { JsonValue } from '@angular-devkit/core';
import { UpdateRecorder } from '@angular-devkit/schematics';
import { Node } from 'jsonc-parser';
export declare function appendPropertyInAstObject(recorder: UpdateRecorder, node: Node, propertyName: string, value: JsonValue, indent: number): void;
export declare function insertPropertyInAstObjectInOrder(recorder: UpdateRecorder, node: Node, propertyName: string, value: JsonValue, indent: number): void;
