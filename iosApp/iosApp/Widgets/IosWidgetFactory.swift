//
//  IosWidgetFactory.swift
//  iosApp
//
//  Created by Aleksey Mikhailov on 13.01.2023.
//  Copyright © 2023 IceRock Development. All rights reserved.
//

import UIKit
import shared_ios

class IosWidgetFactory: WidgetFactory {
    func BannersColumn() -> BannersColumn {
        IosWidgetBannersColumn()
    }
    
    func Box() -> Box {
        IosWidgetBox()
    }
    
    func SearchRow() -> SearchRow {
        IosWidgetSearch()
    }
    
    func ImageCardWithText() -> ImageCardWithText {
        IosWidgetImageCardWithText()
    }
    
    func Chip() -> Chip {
        IosWidgetChip()
    }
    
    func Tabs() -> Tabs {
        IosWidgetTabs()
    }
    
    func HeaderFooterContent() -> HeaderFooterContent {
        IosWidgetFooterColumn()
    }
    
    func Onboarding() -> Onboarding {
        IosWidgetOnboarding()
    }
    
    func Divider() -> Divider {
        IosWidgetDivider()
    }
    
    func ListItem() -> ListItem {
        IosWidgetListItem()
    }
    
    func Switch() -> Switch {
        IosWidgetSwitch()
    }
    
    func CounterButton() -> CounterButton {
        IosWidgetCounter()
    }
    
    func ProductCard() -> ProductCard {
        IosWidgetProductCard()
    }
    
    func RowWithWeight() -> RowWithWeight {
        IosRowWithWeight()
    }
    
    func ShortButton() -> ShortButton {
        IosShortButton()
    }
    
    func Banners() -> Banners {
        IosWidgetBanners()
    }
    
    func Space() -> Space {
        IosWidgetSpace()
    }
    
    
    func Button() -> Button {
        IosWidgetButton()
    }
    
    func Card() -> Card {
        IosWidgetCard()
    }
    
    func ImageButton() -> ImageButton {
        IosImageButton()
    }
    
    func Stack() -> Stack {
        IosStack()
    }
    
    func Image() -> Image {
        IosWidgetImage()
    }
    
    func Text() -> Text {
        IosWidgetText()
    }
    
    func TextInput() -> TextInput {
        IosWidgetTextInput()
    }
}
