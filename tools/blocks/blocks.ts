import MCData from 'minecraft-data'
import fs from 'fs'
import path from 'path'

const version = '1.18'

console.log(`Generating sources for ${version}...`)
const data: MCData.IndexedData = MCData(version)

let staticVars = ''
let listItems = ''

data.blocksArray.forEach(block => {
    console.log(block.id)

    let staticVar = `public static final Block ${block.name.toUpperCase()} = getBlock(${block.id});\n\t`

    let harvestTools = ""
    if (block.harvestTools != null) {
        let tools = ""
        for (const toolId in block.harvestTools) {
            tools += `${toolId}, `
        }
        harvestTools += `, new int[]{${tools}}`
    }

    let listItem = `new Block(${block.id}, "${block.displayName}", "${block.name}", ${block.hardness ? block.hardness.toString() : '0.0'}, ${block.resistance ? block.resistance.toString() : '0.0'}, ${block.minStateId}, ${block.maxStateId}, ${block.defaultState}, ${block.diggable}, ${block.emitLight}, "${block.material}"${harvestTools}),\n\t\t`

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
