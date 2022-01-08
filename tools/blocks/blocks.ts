import MCData from 'minecraft-data'
import fs from 'fs'
import path from 'path'
const version = '1.18'

console.log(`Generating sources for ${version}...`)
const data: MCData.IndexedData = MCData(version)

let staticVars = ''
let listItems = ''

data.blocksArray.forEach(block => {
  let staticVar = 'public static final Block '
  staticVar += block.name.toUpperCase()
  staticVar += ' = getBlock(' + block.id + ');\n\t'

  let listItem = 'new Block('
  listItem += block.id + ', '
  listItem += '"' + block.displayName + '", '
  listItem += '"' + block.name + '", '
  listItem += ((block.hardness) ? block.hardness.toString() : '0.0') + ', '
  listItem += ((block.resistance) ? block.resistance.toString() : '0.0') + ', '
  listItem += block.minStateId + ', '
  listItem += block.maxStateId + ', '
  listItem += block.defaultState + ', '
  listItem += block.diggable + ', '
  listItem += block.emitLight + ', '
  listItem += '"' + block.material + '"'
  if (block.harvestTools != null) {
    listItem += ', new int[]{'
    for (const toolId in block.harvestTools) {
      listItem += toolId + ', '
    }
    if (listItem.endsWith(', ')) listItem = listItem.slice(0, -3)
    listItem += '}'
  }
  listItem += '),\n\t\t'

  listItems += listItem
  staticVars += staticVar
})

if (listItems.endsWith(',\n\t\t')) listItems = listItems.slice(0, -4)

fs.readFile(path.join(__dirname, 'BlockClass.template'), (err, buffer) => {
  if (err != null) throw err

  let data = buffer.toString()
  data = data.replace('%statics%', staticVars)
  data = data.replace('%list_items%', listItems)

  fs.writeFile(path.join(__dirname, 'Block.java'), data, (err) => {
    if (err != null) throw err
    console.log('Successfully generated Block class!')
  })
})
