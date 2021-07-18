
# UpdateChecker, the user-friendly updater
This is a Java Library that aims to be fully customizable by the end-user.

From the settings or through the API you can:
* Enable or disable the Lib
* Enabled or disable the changelog
* Choose to notify only if there are bug-fixes

## How it works
When the program start-up if the library is enabled, it will check for updates and relaying on its settings
it will notify the user telling him what are the news.

## Installation
At the moment you must clone the project and importing manually into your project

## Usage
This library uses the jar's manifest to know which version of the program
the user is currently using and other useful info so make sure to
have it correctly set up.

### What the manifest file needs

1. "Specification-Version" it tells the version of the jar
   
1. "Latest-ManifestURL" it is a URL that points to the manifest of the latest jar

1. "Changelog-FolderURL" it is a URL that points to a folder which contains all the jar's changelog

1. "Changelog-NamingConvention" it tells the naming convention used into the changelog folder

1. "Jar-DownloadURL" it tells from which URL you can download the jar

```
manifest.mf example:

    Specification-Version: x.y.z
    Latest-ManifestURL: http://raw.github.com/mylatestmanifest.mf
    
    Changelog-FolderURL: http://raw.github.com/changelogs/
    Changelog-NamingConvetion: changelog-x-y-z.log
    Jar-DownloadURL: http://github.com/repo/demo/x.y.z/demo-release.jar 
```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.
Please make sure to update tests as appropriate.