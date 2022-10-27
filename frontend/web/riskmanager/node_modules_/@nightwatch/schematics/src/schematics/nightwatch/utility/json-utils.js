"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.insertPropertyInAstObjectInOrder = exports.appendPropertyInAstObject = void 0;
function _buildIndent(count) {
    return '\n' + new Array(count + 1).join(' ');
}
function appendPropertyInAstObject(recorder, node, propertyName, value, indent) {
    var _a;
    const indentStr = _buildIndent(indent);
    if (((_a = node.parent) === null || _a === void 0 ? void 0 : _a.children) && node.parent.children.length > 0) {
        // Insert comma.
        recorder.insertLeft(node.offset + node.length, ',');
    }
    if (node.parent) {
        recorder.insertRight(node.offset + node.length, indentStr +
            `"${propertyName}": ${JSON.stringify(value, null, 2).replace(/\n/g, indentStr)}` +
            indentStr.slice(0, -2));
    }
    else {
        throw new Error(`Cannot append property [${propertyName}] in root.`);
    }
}
exports.appendPropertyInAstObject = appendPropertyInAstObject;
function insertPropertyInAstObjectInOrder(recorder, node, propertyName, value, indent) {
    if (node.children === undefined) {
        throw new Error(`Failed to insert JSON property ${propertyName}`);
    }
    //Find insertion info.
    let insertAfterProp = null;
    let prev = null;
    let isLastProp = false;
    const last = node.children[node.children.length - 1];
    for (const prop of node.children) {
        if (prop.children === undefined)
            continue;
        if (prop.children[0].value > propertyName) {
            if (prev) {
                insertAfterProp = prev;
            }
            break;
        }
        if (prop === last) {
            isLastProp = true;
            insertAfterProp = last;
        }
        prev = prop;
    }
    if (isLastProp) {
        appendPropertyInAstObject(recorder, last, propertyName, value, indent);
        return;
    }
    const indentStr = _buildIndent(indent);
    const insertIndex = insertAfterProp === null
        ? node.offset + 1
        : insertAfterProp.offset + insertAfterProp.length + 1;
    const insertString = `${indentStr}"${propertyName}": ${JSON.stringify(value, null, 2).replace(/\n/g, indentStr)}` +
        (node.children && node.children.length > 0 ? ',' : '\n');
    recorder.insertRight(insertIndex, insertString);
}
exports.insertPropertyInAstObjectInOrder = insertPropertyInAstObjectInOrder;
//# sourceMappingURL=json-utils.js.map