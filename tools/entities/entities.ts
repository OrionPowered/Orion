import MCData from 'minecraft-data'
import fs from 'fs'
import path from 'path'
const version = '1.18'

console.log(`Generating sources for ${version}...`)
const data: MCData.IndexedData = MCData(version)

let staticVars: string = ''
let listItems: string = ''

data.entitiesArray.forEach(entity => {
  let staticVar = `public static final EntityType ${entity.name.toUpperCase()} = getById(${entity.id});\n\t`

  let listItem = `new EntityType(${entity.id}, ${entity.internalId}, "${entity.name}", "${entity.displayName}", ${entity.width}, ${entity.height}, Entity.Category.${entity.type.toUpperCase()}),\n\t\t`

  listItems += listItem
  staticVars += staticVar
})

if (listItems.endsWith(',\n\t\t')) listItems = listItems.slice(0, -4)

fs.readFile(path.join(__dirname, 'EntityClass.template'), (err, buffer) => {
  if (err != null) throw err

  let data = buffer.toString()
  data = data.replace('%statics%', staticVars)
  data = data.replace('%list_items%', listItems)

  fs.writeFile(path.join(__dirname, 'EntityType.java'), data, (err) => {
    if (err != null) throw err
    console.log('Successfully generated EntityType class!')
  })
})
