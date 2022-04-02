# Orion
### [![PrysmNetwork](https://circleci.com/gh/PrysmNetwork/Orion.svg?style=svg)]()
Orion is a 1.18.2 (758) Minecraft Server and Proxy implementation written to be the absolute bare-bones to run a network, 
but extendable for maximum customization.

<p align="center">
  <img src="https://user-images.githubusercontent.com/38387876/148739079-b49bf96a-86e1-4425-a600-b709c60524bc.png" />
</p>

## Progress
### Common
- [x] Multithreaded TCP pipeline
- [x] Packet structure
- [x] Encryption (using velocity's native encryption library)
- [ ] Compression
- [x] Event system
- [x] Plugin & module loading
- [x] Mojang Authentication
- [x] Offline Mode Support
- [ ] In memory shared object storage
- [ ] Tab list 
- [x] Chat

### Server
- [x] Packet handlers
- [ ] Player movement & mechanics
- [ ] Player permissions
- [ ] Player data containers
- [ ] Velocity Support

### Proxy
- [ ] Proxy <-> Backend Encryption
- [ ] Server switching
- [ ] Forced hosts
- [ ] Persistent connection to backend Orion servers (for custom protocols)

## Development Requirements
- JDK 17 & Gradle

## Modules & Plugins

### Modules

Modules are meant to hook into any of Orion's internal classes so that they may extend and/or add additional
functionality. For example, PVP will not be part of Orion out of the box; but instead, a module for PVP will be created.
This means servers that do not need PVP, such as lobbies, simply ignore any PVP mechanics clients attempt and therefore,
save resources. On the other hand, servers that need PVP can simply load the module.

### Plugins

Plugins do not have access to Orion's internal classes and instead will use the `api` package to interact with the
server. This is a high level API that is meant to be 100% safe. A good use case for a plugin would be modifying chat
format, tracking player stats, etc. 
