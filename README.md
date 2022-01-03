# Orion
Orion is a 1.18 (757) Minecraft server implementation written with the goal of being the absolute bare-bones to run a lobby, minigames, or any other server that doesn't require advanced vanilla mechanics.

## Progress
- [x] Multi-threaded TCP pipeline
- [x] Packet structure
- [x] Packet handlers
- [x] MOTD/Server Status
- [x] Mojang Authentication
- [x] Encryption (using velocity's native encryption library)
- [ ] Compression
- [x] Event system
- [x] Plugin & module loading
- [ ] Chunk loading (*in progress*) 
- [ ] Player movement & mechanics
- [ ] Chat
- [ ] Tab list
- [ ] Player permissions
- [ ] Player data containers
- [ ] Velocity Support

## Setting up
To build dependencies, you must have Rust and Cargo installed as we depend on Rust libraries. Additionally, GNU make is used for building.

### Building Dependencies
```sh
make setup
```

## Modules & Plugins
### Modules
Modules are meant to hook into any of Orion's internal classes so that they may extend and/or add additional functionality. For example, PVP will not be part of Orion out of the box; but instead, a module for PVP will be created. This means servers that do not need PVP, such as lobbies, simply ignore any PVP mechanics clients attempt and therefore, save resources. On the other hand, servers that need PVP can simply load the module.

### Plugins 
Plugins do not have access to Orion's internal classes and instead will use the `api` package to interact with the server. This is a high level API that is meant to be 100% safe. A good use case for a plugin would be modifying chat format, tracking player stats, etc. 
