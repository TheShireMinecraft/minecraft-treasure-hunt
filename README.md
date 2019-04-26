# spigot-easteregghunt
Minecraft server Easter egg hunt using WorldGuard regions.

## Configuration options
```
eggHuntEnabled: false
apiEndpoint: ''
apikey: ''
hunts:
  world_name:
    region_name:
      enabled: true
```

### eggHuntEnabled
true if the plugin should attempt to initialise and start egg hunts.
false if egg hunts are globally disabled and the plugin should do nothing.

### apiEndpoint
URL of API endpoint for keeping track of player scores, without a trailing slash.
_e.g. apiEndpoint: 'https://example.com/egg-hunt-api'_

### apiKey
Token for authenticating your server against the score tracking API

### hunts
_**World and region names must be written in lowercase letters in config.yml**_
Tree comprising world names, and regions within those worlds where egg hunts are to be configured.
Set 'enabled' _(boolean)_ below a region name to either **true** or **false** to enable or disable an egg hunt in this region.