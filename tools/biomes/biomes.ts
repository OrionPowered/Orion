import MCData from 'minecraft-data'
import fs from 'fs'
import path from 'path'
const version = '1.18'

console.log(`Generating sources for ${version}...`)
const data: MCData.IndexedData = MCData(version)

let staticVars: string = ''
let listItems: string = ''

data.biomesArray.forEach(biome => {
  let staticVar = `public static final Biome ${biome.name.toUpperCase()} = getBiome(${biome.id});\n\t`

  let listItem = `new Biome(${biome.id}, "${biome.name}", "${biome.category}", ${biome.temperature}, "${biome.precipitation}", ${biome.depth}, "${biome.dimension}", "${biome.displayName}", ${biome.color}, ${biome.rainfall}),\n\t\t`

  listItems += listItem
  staticVars += staticVar
})

if (listItems.endsWith(',\n\t\t')) listItems = listItems.slice(0, -4)

fs.readFile(path.join(__dirname, 'BiomeClass.template'), (err, buffer) => {
  if (err != null) throw err

  let data = buffer.toString()
  data = data.replace('%statics%', staticVars)
  data = data.replace('%list_items%', listItems)

  fs.writeFile(path.join(__dirname, 'Biome.java'), data, (err) => {
    if (err != null) throw err
    console.log('Successfully generated Biome class!')
  })
})
