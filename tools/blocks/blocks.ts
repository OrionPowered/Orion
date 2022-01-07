import MCData from 'minecraft-data'
import fs from "fs";
import path from "path";
const version = "1.18"

console.log(`Generating sources for ${version}...`);
const data: MCData.IndexedData = MCData(version);

let staticVars: string = "";

for (let name in data.blocksByName) {
    let block = data.blocksByName[name];

    let string: string = "public static final Block "
    string += name.toUpperCase();
    string += " = new Block(";
    string += block.id + ", ";
    string += "\"" + block.displayName + "\", ";
    string += "\"" + block.name + "\", ";
    string += ((block.hardness) ? block.hardness.toString() : "0.0") + ", ";
    string += ((block.resistance) ? block.resistance.toString() : "0.0") + ", ";
    string += block.minStateId + ", ";
    string += block.maxStateId + ", ";
    string += block.defaultState + ", ";
    string += block.diggable + ", ";
    string += block.emitLight + ", ";
    string += "\"" + block.material + "\"";
    if (block.harvestTools) {
        string += ", new int[]{"
        for (let toolId in block.harvestTools) {
            string += toolId + ", "
        }
        if (string.endsWith(", ")) string = string.slice(0, -3);
        string += "}"
    }
    string += ");"

    staticVars += string + "\n\t";
}

fs.readFile(path.join(__dirname, "BlockClass.template"), (err, buffer) => {
    if (err) throw err;

    let data: string = buffer.toString();
    data = data.replace("%statics%", staticVars);

    fs.writeFile(path.join(__dirname, "Block.java"), data, (err) => {
        if (err) throw err;
        console.log("Successfully generated Block class!");
    });
});
