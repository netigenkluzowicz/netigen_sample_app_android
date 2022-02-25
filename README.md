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
    



