import MCData from 'minecraft-data'
const version = "1.18"

console.log(`Generating sources for ${version}...`);
const data: MCData.IndexedData = MCData(version);

console.log(data.materials);
