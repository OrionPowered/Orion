const https = require("https");
module.exports = class {
    _attributes;
    _blocks;
    _blockCollisionShapes;
    _biomes;
    _enchantments;
    _effects;
    _items;
    _recipes;
    _instruments;
    _materials;
    _entities;
    _protocol;
    _windows;
    _version;
    _language;
    _foods;
    _particles;
    _tints;
    _mapIcons;
    constructor(version) {
        this._loadPaths(version);
    }

    _request(path, callback) {
        const options = {
            hostname: "raw.githubusercontent.com",
            path: "/PrismarineJS/minecraft-data/master/data/" + path,
            method: "GET"
        }
        console.log(options);

        const req = https.request(options, res => {
            let data;

            res.on('data', (chunk) => {
                data += chunk;
            });

            res.on("end", () => {
                if (data.startsWith("undefined")) data = data.substring(9);
                callback(null, data);
            });
        })

        req.on('error', error => {
            callback(error, null);
        })

        req.end()
    }

    _loadPaths(version) {
        this._request("dataPaths.json", (error, data) => {
           if (error) throw error;
            data = JSON.parse(data)["pc"][version];
            if (data) {
                this._attributes = data["attributes"] + "/attributes.json";
                this._blocks = data["blocks"] + "/blocks.json";
                this._blockCollisionShapes = data["blockCollisionShapes"] + "/blockCollisionShapes.json";
                this._biomes = data["biomes"] + "/biomes.json";
                this._enchantments = data["enchantments"] + "/enchantments.json";
                this._effects = data["effects"] + "effects.json";
                this._items = data["items"] + "/items.json";
                this._recipes = data["recipes"] + "/recipes.json";
                this._instruments = data["instruments"] + "/instruments.json";
                this._entities = data["entities"] + "/entities.json";
                this._protocol = data["protocol"] + "/protocol.json";
                this._windows = data["windows"] + "/windows.json";
                this._version = data["version"] + "/version.json";
                this._language = data["language"] + "/language.json";
                this._foods = data["foods"] + "/foods.json";
                this._particles = data["particles"] + "/particles.json";
                this._tints = data["tints"] + "/tints.json";
                this._mapIcons = data["mapIcons"] + "/mapIcons.json";
            } else {
                console.error("Version not found.");
                process.exit();
            }
        });
    }


    attributes(callback) {
        this._request(this._attributes, (error, data) => {
            if (error) callback(error);
            else callback(data);
        });
    }

    blocks(callback) {
        this._request(this._blocks, (error, data) => {
            if (error) callback(error);
            else callback(data);
        });
    }

    blockCollisionShapes(callback) {
        this._request(this._blockCollisionShapes, (error, data) => {
            if (error) callback(error);
            else callback(data);
        });
    }

    biomes(callback) {
        this._request(this._biomes, (error, data) => {
            if (error) callback(error);
            else callback(data);
        });
    }

    enchantments(callback) {
        this._request(this._enchantments, (error, data) => {
            if (error) callback(error);
            else callback(data);
        });
    }

    effects(callback) {
        this._request(this._effects, (error, data) => {
            if (error) callback(error);
            else callback(data);
        });
    }

    items(callback) {
        this._request(this._items, (error, data) => {
            if (error) callback(error);
            else callback(data);
        });
    }

    recipes(callback) {
        this._request(this._recipes, (error, data) => {
            if (error) callback(error);
            else callback(data);
        });
    }

    instruments(callback) {
        this._request(this._instruments, (error, data) => {
            if (error) callback(error);
            else callback(data);
        });
    }

    materials(callback) {
        this._request(this._materials, (error, data) => {
            if (error) callback(error);
            else callback(data);
        });
    }

    entities(callback) {
        this._request(this._entities, (error, data) => {
            if (error) callback(error);
            else callback(data);
        });
    }

    protocol(callback) {
        this._request(this._protocol, (error, data) => {
            if (error) callback(error);
            else callback(data);
        });
    }

    windows(callback) {
        this._request(this._windows, (error, data) => {
            if (error) callback(error);
            else callback(data);
        });
    }

    language(callback) {
        this._request(this._language, (error, data) => {
            if (error) callback(error);
            else callback(data);
        });
    }

    foods(callback) {
        this._request(this._foods, (error, data) => {
            if (error) callback(error);
            else callback(data);
        });
    }

    particles(callback) {
        this._request(this._particles, (error, data) => {
            if (error) callback(error);
            else callback(data);
        });
    }

    tints(callback) {
        this._request(this._tints, (error, data) => {
            if (error) callback(error);
            else callback(data);
        });
    }

    mapIcons(callback) {
        this._request(this._mapIcons, (error, data) => {
            if (error) callback(error);
            else callback(data);
        });
    }
}