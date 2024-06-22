# vf

A JavaScript port of the [Vineflower decompiler](https://github.com/Vineflower/vineflower).

## Example

```js
const fs = require("fs");
const { decompile } = require("./vf.js"); // get it from the dist/ directory or jsDelivr

const data = fs.readFileSync("./your/package/HelloWorld.class"); // read a class file
console.log(decompile("your/package/HelloWorld", {
    source: (name) => {
        /* provide classes for analysis here, including the one you want to decompile */
        
        console.log(name); /* internal name, e.g. java/lang/Object */
        return name === "your/package/HelloWorld" ? data : null /* class not available */;
    },
    resources: [
        /* class names of supporting resources (libraries), which `source` can load */
        // "java/lang/Object",
    ],
    options: {
        /* see https://github.com/Vineflower/vineflower/blob/master/src/org/jetbrains/java/decompiler/main/extern/IFernflowerPreferences.java#L11 */
        "banner": "// Decompiled with Vineflower in TeaVM!\n", /* testing option - comment on top of the decompiled code */
    },
}));
```

Or see the browser-based proof-of-concept in the [docs](./docs) directory.

## Licensing

The supporting code for this project is licensed under the [MIT License](./LICENSE).
The Vineflower decompiler is licensed under the [Apache License 2.0](./LICENSE-VF).

_This project is not affiliated with, maintained or endorsed by the Vineflower project in any way.
Do NOT report issues with this project to the Vineflower issue tracker._
