# Netigen sample app android

### Kotlin Coding convention

We use [Kotlin coding conventions](https://kotlinlang.org/docs/coding-conventions.html)

Apply the style guide

    Go to Settings/Preferences | Editor | Code Style | Kotlin.

    Click Set from....

    Select Kotlin style guide.

Verify that your code follows the style guide

    Go to Settings/Preferences | Editor | Inspections | Kotlin.

    Open Kotlin | Style issues.

    Switch on File is not formatted according to project settings inspection. 
    Additional inspections that verify other issues described in the style guide 
    (such as naming conventions) are enabled by default.
    
We use [ktlint Idea plugin](https://plugins.jetbrains.com/plugin/15057-ktlint-unofficial-) with experimental enabled.
It automatically runs ktlint on Kotlin files, and annotates any errors found.

Enable and configure in Preferences > Tools > ktlint.

Our editor config (.editconfig):

    root = true
    
    [*.{java,kt,kts,xml,kt.spec,kts.spec}]
    # noinspection EditorConfigKeyCorrectness
    disabled_rules = no-wildcard-imports, import-ordering
    charset = utf-8
    insert_final_newline = true
    ij_kotlin_allow_trailing_comma = true
    ij_kotlin_allow_trailing_comma_on_call_site = true
    max_line_length = 150
    end_of_line = crlf

### Naming
1. Drawables

File names should only consist of:

    lowercase letters of the English alphabet (without Polish characters etc.),
    underscores (do not use periods, semicolons, etc.)
    digits (but not at the beginning)

We adopt the following naming conventions:

    names begin with a graphic type prefix:
        backgrounds - bg_
        icons - ic_
        buttons (buttons) - btn_
        other images - img_

    names should be descriptive and in English e.g .:
    bg_bottom_location_list - background at the bottom of the location list screen

    if the same object has different states / variants, etc., we try to convey it in the description, e.g.
    bg_no_ads_rectangle - rectangular no ads button
    bg_no_ads_sale_square - no ads square button during the promotion
    btn_add and then:
    _pressed / _normal / _disabled / _selected - example button states etc.
    
2. Layouts

Layout files should match the name of the Android components that they are intended for but moving the top level component name to the beginning. For example, if we are creating a layout for the `SignInActivity`, the name of the layout file should be `activity_sign_in.xml`.

Component        | Prefix      | Class Name                 | Layout Name
-----------------|-------------|----------------------------|----------------------
Activity         | `activity_` | MainPageActivity           | `activity_main.xml`
Fragment         | `fragment_` | DetailsPageFragment        | `fragment_details.xml`
Dialog           | `dialog_`   | NewItemPageDialogFragment  | `dialog_new_item.xml`
AdapterView item | `item_`     | PersonViewHolder           | `item_person.xml`
Custom view layout | `view_`   | ProfileAvatarView          | `view_profile_avatar.xml`

A slightly different case is when we are creating a layout that is going to be inflated by an `Adapter`, e.g to populate a `ListView`. In this case, the name of the layout should start with `item_`.