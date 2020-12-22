# spigot-easteregghunt
Treasure hunts for WorldGuard regions. Supports poly and cuboid regions only. Supported hunt types are 'easter', 'halloween' and 'christmas'. Plugin can throw a JSON payload at a given endpoint whenever an item is found.

This plugin is provided free of charge with no guarantees that it will work, and no promise of support. Should you stumble upon this and try to use it, you're welcome to report any issues you may encounter in the 'Issues' tab and I'll try to help you. If you are trying to use the plugin without meeting the requirements set out below, this is not supported and I will be unable to offer any assistance.

## Requirements
- Spigot or Paper v1.16.4
- Item NBT API v2.6

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

The data sent in the JSON payload has the following keys:
- uuid (player UUID)
- name (player name)
- egg (name of the item found)
- region (ID of the WorldGuard region the item was found in)

The 'API Key' is sent as a query parameter in the GET request to the endpoint, with a name of 'k'.

### apiKey
Token for authenticating your server against the score tracking API

### hunts
_**World and region names must be written in lowercase letters in config.yml**_
Tree comprising world names, and regions within those worlds where egg hunts are to be configured.
Set 'enabled' _(boolean)_ below a region name to either **true** or **false** to enable or disable an egg hunt in this region.
