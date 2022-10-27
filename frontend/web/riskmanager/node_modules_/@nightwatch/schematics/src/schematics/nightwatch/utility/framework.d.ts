import { Tree } from '@angular-devkit/schematics';
export declare const ANGULAR = "angular";
export declare const REACT = "react";
export declare const REACT_TS = "react-ts";
export declare const REACT_NATIVE = "react-native";
export declare const TYPESCRIPT = "typescript";
export default function getFramework(host: Tree): "angular" | "react" | "react-ts" | "react-native" | "typescript";
