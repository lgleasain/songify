# Songify

This app lists out tags, categories and songs based on the following API all as mocked items.

Fetch the list of tags:
    Route:
    /api/1/tags

    Method:
    GET

    Result: Renders a list of objects, each of which has the following fields:
    title: The tag title (string)
    id: The tag id (string)


Fetch the list of categories with a tag:
    Route:
    /api/1/category/tag/{tag_id}

    Method:
    GET

    Result: Renders a list of objects, each of which has the following fields:
    name: The category name (string)
    id: The category id (string)
    song_ids: The list of songs in the category (list of int)

Fetch extended information for a list of songs:
    Route:
    /api/1/songs/multi

    Method:
    GET

    Optional parameters:
    id: A song id.  More than one of these can be given. If multiple songs are required, specify id multiple times.
      ie: /api/1/songs/multi?id=1&id=2

    Result: Renders a (possibly empty) list of song objects, one for each id parameter.  It is NOT an error for an id to be invalid; any such ids are silently skipped.

    id: integer song id
    name: song name
    type: one of {'basic', 'artist', 'stream'
    description: some descriptive text
    cover_url: URL for the song's cover art.

## Notable Implemention Details

* Web calls are done via Retrofit and the app is using the MockWebServer to mock out requests for testing etc..
* Dagger is being used so that dependencies for testing etc. can be injected, though in the current form of the app not all of this is being used (I'm violating YAGNI a little bit here).
* Espresso tests are using idlingresources hooks to handle resource idling.  It is currently using the deprecated version of this and will need a refactor to update it to the new format introduced over the summer.
* RxJava is being used to reduce callback hell.
* Glide is being used for the album cover images.
* This is using the ViewModel from the Android Architecture Components, though not being used that much at the moment(IE: I've locked the screen to portrait because I didn't get to testing orientation changes).
* The app follows a MVVM architecture and is using Data Binding.
* I haven't done a lot of crazy styling to this, so that could use some improvement.
* 
