const VersionData = require("./version_data.js");
const version = "1.18"

console.log(`Generating sources for ${version}...`);
const data = new VersionData(version);

data.materials((error, materials) => {
    if (error) throw error;
    console.log(materials);
})
