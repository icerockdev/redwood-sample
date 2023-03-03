//
//  ImageCardWithText.swift
//  iosApp
//
//  Created by alobynya on 22.02.2023.
//  Copyright © 2023 IceRock Development. All rights reserved.
//

import UIKit
import shared_ios


class IosWidgetImageCardWithText: UIViewController, ImageCardWithText {

    @IBOutlet weak var label: UILabel!
    @IBOutlet weak var image: UIImageView!
    
    private let textView : UILabel =  {
        let text = UILabel()
        text.translatesAutoresizingMaskIntoConstraints = false
        return text
    }()
    
    override func loadView() {
        self.view = textView
    }
    
    override func viewDidLoad() {
    //    container.addSubview(image)
    }
    
    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()
    }
    
    func height(height: EntitySize?) {
  
    }
    
    func width(width_ width: EntitySize?) {
        
    }
    
    func onClick(onClick: (() -> Void)? = nil) {
        
    }
    
    func placeholder(placeholder: ImageResource?) {
        
    }
    
    func text(text: StringDesc) {
       textView.text = text.localized()
    }
    
    func textBackgroundColor(textBackgroundColor: GraphicsColor?) {
        
    }
    
    func textColor(textColor: GraphicsColor?) {
        
    }
    
    func url(url: String?) {
        
    }
    
    
    var layoutModifiers: Redwood_runtimeLayoutModifier = ExposedKt.layoutModifier()
    
    var value: Any {view}
}
