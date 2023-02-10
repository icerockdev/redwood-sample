//
//  ProductCardController.swift
//  iosApp
//
//  Created by alobynya on 08.02.2023.
//  Copyright © 2023 IceRock Development. All rights reserved.
//

import UIKit
import shared_ios

class ProductCardController : UIViewController{
    
    
    private let image : UIImageView =  {
        let image = UIImageView()
        image.contentMode = .scaleAspectFill
        image.layer.cornerRadius = 8
        image.clipsToBounds = true
        image.translatesAutoresizingMaskIntoConstraints = false
             return image
    }()
    
    private let likeView : UIImageView = {
        let likeView = UIImageView()
        likeView.clipsToBounds = true
        likeView.layer.cornerRadius = 16
        likeView.layer.masksToBounds = true
        likeView.contentMode = .center
        likeView.layer.zPosition = 2
        likeView.backgroundColor = black60
        likeView.translatesAutoresizingMaskIntoConstraints = false
           return likeView
    }()
    
    private let badge : UITextView = {
         let badge = UITextView()
        badge.textContainerInset = UIEdgeInsets(top: 2, left: 6, bottom: 2, right: 6);
        badge.backgroundColor = errorColor
        badge.textColor = .white
        badge.font = .systemFont(ofSize: 12)
        badge.layer.cornerRadius = 10
        badge.layer.zPosition = 2
        badge.layer.masksToBounds = true
        badge.translatesAutoresizingMaskIntoConstraints = false
         return badge
    }()
    
    private let costView : UILabel = {
        let badge = UILabel()
        badge.font = .boldSystemFont(ofSize: 17)
        badge.translatesAutoresizingMaskIntoConstraints = false
        return badge
    }()
    
    private let oldCostView : UILabel = {
        let badge = UILabel()
        badge.font = .systemFont(ofSize: 15)
        badge.translatesAutoresizingMaskIntoConstraints = false
          return badge
    }()
    
    private let titleView : UILabel = {
        let badge = UILabel()
        badge.font = .systemFont(ofSize: 15)
        badge.translatesAutoresizingMaskIntoConstraints = false
       return badge
    }()
    
    private let subtitleView : UILabel = {
        let badge = UILabel()
        badge.font = .boldSystemFont(ofSize: 12)
        badge.textColor = secondaryColor
        badge.translatesAutoresizingMaskIntoConstraints = false
        return badge
    }()
    
    private let fotterView : UILabel = {
        let badge = UILabel()
        badge.font = .systemFont(ofSize: 12)
        badge.translatesAutoresizingMaskIntoConstraints = false
        return badge
    }()
    
    private let container : ProductCardView = {
        let container = ProductCardView()
        container.translatesAutoresizingMaskIntoConstraints = true
        return container
    }()
    
    override func loadView() {
        self.view = container
      
    }
    
    override func viewDidLoad() {
        container.addSubview(image)
        container.addSubview(costView)
        container.addSubview(likeView)
        container.addSubview(badge)
        container.addSubview(oldCostView)
        container.addSubview(titleView)
        container.addSubview(subtitleView)
        container.addSubview(fotterView)
        
    }
    
    var flag = true
    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()
        image.frame = CGRect(x: 19, y: 8, width: view.frame.width - 38, height:
                                view.frame.width - 38)
        image.layer.zPosition = 1.0
        likeView.frame = CGRect(x: view.frame.width - 44, y: 12, width: 32, height: 32)
        let badgeSize = badge.sizeThatFits(CGSize(width: 200, height: 200))
        badge.frame = CGRect(x: 12, y: view.frame.width - 52, width: badgeSize.width, height: badgeSize.height)
        let costViewSize = costView.sizeThatFits(view.frame.size)
     
        costView.widthAnchor.constraint(equalToConstant: CGFloat(costViewSize.width)).isActive = true
        costView.heightAnchor.constraint(equalToConstant: CGFloat(costViewSize.height)).isActive = true
        costView.leftAnchor.constraint(equalTo: container.leftAnchor, constant: 12).isActive = true
        costView.topAnchor.constraint(equalTo: container.topAnchor, constant: image.frame.width + 16).isActive = true
        let oldCostViewSize  = oldCostView.sizeThatFits(view.frame.size)
        oldCostView.widthAnchor.constraint(equalToConstant: CGFloat(oldCostViewSize.width)).isActive = true
        oldCostView.heightAnchor.constraint(equalToConstant: CGFloat(oldCostViewSize.height)).isActive = true
        oldCostView.leftAnchor.constraint(equalTo: costView.rightAnchor, constant: 4).isActive = true
        oldCostView.centerYAnchor.constraint(equalTo: costView.centerYAnchor).isActive = true
        
        let titleViewSize  = titleView.sizeThatFits(view.frame.size)
        titleView.widthAnchor.constraint(equalToConstant: CGFloat(titleViewSize.width)).isActive = true
        titleView.heightAnchor.constraint(equalToConstant: CGFloat(titleViewSize.height)).isActive = true
        titleView.topAnchor.constraint(equalTo: costView.bottomAnchor, constant: 4).isActive = true
        titleView.leftAnchor.constraint(equalTo: container.leftAnchor, constant: 12).isActive = true
        let subtitleViewSize  = subtitleView.sizeThatFits(view.frame.size)
        subtitleView.widthAnchor.constraint(equalToConstant: CGFloat(subtitleViewSize.width)).isActive = true
        subtitleView.heightAnchor.constraint(equalToConstant: CGFloat(subtitleViewSize.height)).isActive = true
        subtitleView.topAnchor.constraint(equalTo: titleView.bottomAnchor, constant: 4).isActive = true
        subtitleView.leftAnchor.constraint(equalTo: container.leftAnchor, constant: 12).isActive = true
        
        let fotterViewSize  = fotterView.sizeThatFits(view.frame.size)
        fotterView.widthAnchor.constraint(equalToConstant: CGFloat(fotterViewSize.width)).isActive = true
        fotterView.heightAnchor.constraint(equalToConstant: CGFloat(fotterViewSize.height)).isActive = true
        fotterView.topAnchor.constraint(equalTo: subtitleView.bottomAnchor, constant: 56).isActive = true
        fotterView.leftAnchor.constraint(equalTo: container.leftAnchor, constant: 12).isActive = true
       // container.bottomAnchor.constraint(equalTo: fotterView.bottomAnchor).isActive = true
    }
    
    func setIsLeked(value:Bool){
        if(value){
            likeView.image = UIImage(named: "like_clicked")
        }else{
            likeView.image = UIImage(named: "like")
        }
    }
    
    func setBadge(value: String?){
        badge.text = value
        badge.isHidden = value == nil
    }
    
    func setImage(url: String){
        image.load(url: URL(string:url)!)
    }
    
    func setCost(value: String?){
        costView.text = value
    }
    
    func setOldCost(value: String?){
        if( value != nil){
            let attributeString: NSMutableAttributedString =  NSMutableAttributedString(string: value!)
            attributeString.addAttribute(NSAttributedString.Key.strikethroughStyle, value: 1, range: NSMakeRange(0, attributeString.length))
            attributeString.addAttribute(NSAttributedString.Key.strikethroughColor, value: errorColor, range: NSMakeRange(0, attributeString.length))
            oldCostView.attributedText = attributeString
        }else{
            oldCostView.text = value
        }
    }
    
    func setTitle(value: String?){
        titleView.text = value
    }
    
    func setSubtitle(value: String?){
        subtitleView.text = value
    }
    
    func setFooter(value: String?){
        fotterView.text = value
    }
    
    
    func onLikeClick(onClick: (() -> Void)? = nil) {
        if(onClick != nil){
            likeView.setOnClickListener {
                onClick!()
            }
        }
    }
    
    func addAction(view:UIView){
        container.addSubview(view)
        view.translatesAutoresizingMaskIntoConstraints = false
        view.leftAnchor.constraint(equalTo: container.leftAnchor, constant: 12).isActive = true
        view.rightAnchor.constraint(equalTo: container.rightAnchor, constant: -12).isActive = true
        view.topAnchor.constraint(equalTo: subtitleView.bottomAnchor, constant: 4).isActive = true
    }
    
    class ProductCardView: UIView{
        override func sizeThatFits(_ size: CGSize) -> CGSize {
            let originalSize = super.sizeThatFits(size)
            let chilsdsHeight = subviews.max { a, b in
                a.frame.maxY < b.frame.maxY
            }?.frame.maxY
            return CGSize(width: size.width, height:  (chilsdsHeight ?? 344) + 12)
        }
    }
    
}
