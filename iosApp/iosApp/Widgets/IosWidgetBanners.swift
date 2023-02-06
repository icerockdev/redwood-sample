//
//  IosWidgetBanners.swift
//  iosApp
//
//  Created by alobynya on 06.02.2023.
//  Copyright © 2023 IceRock Development. All rights reserved.
//

import UIKit
import shared_ios

class IosWidgetBanners : Banners {
    
  
   
    private let controller: MyBannersController = {
       let container = MyBannersController()
       return container;
    }()
    
    private let root: MyBannersView = {
        let container = MyBannersView()
        container.axis = .horizontal
        container.alignment = .fill
        container.distribution = .fillEqually
        container.translatesAutoresizingMaskIntoConstraints = true
       return container;
    }()
    
    
    func bannersList(bannersList: [EntityBannerData]) {
       controller.setBanners(bannersList: bannersList)
    }
    
    var layoutModifiers: Redwood_runtimeLayoutModifier = ExposedKt.layoutModifier()
  
    var value: Any {
        return controller.view
    }
    
}

class MyBannersContainer: UIView{
    
}


class MyBannersController: UIViewController {
    
    private let scrollView : UIScrollView =  {
        let scrollView = UIScrollView()
        scrollView.layer.cornerRadius = 16
        scrollView.clipsToBounds = true
        scrollView.showsHorizontalScrollIndicator = false
        return scrollView
    }()
    
    private let pageControll : UIPageControl = {
        let pageControll = UIPageControl()
        pageControll.numberOfPages = 3
        pageControll.pageIndicatorTintColor = black60
        pageControll.currentPageIndicatorTintColor = primaryColor
        return pageControll
    }()
    
    private var banners: [EntityBannerData] = []
    
    func setBanners(bannersList: [EntityBannerData]){
        banners = bannersList
        if(view.frame.size.width != 0){
            configureScrollView(bannersList: bannersList)
        }
    }
    
    
    override func loadView() {
        self.view = MyBannersView()
        view.layer.cornerRadius = 16
        view.clipsToBounds = true
    }
    
    override func viewDidLoad() {
        pageControll.addTarget(self, action: #selector(pageControlDidChange(_:)), for: .valueChanged)
        scrollView.delegate = self
        view.addSubview(pageControll)
        view.addSubview(scrollView)
    }
    
    @objc private func pageControlDidChange(_ sender: UIPageControl){
        let current = sender.currentPage
        scrollView.setContentOffset(CGPoint(x:CGFloat(current) * (view.frame.size.width-32), y:0), animated: true)
    }
    
    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()
        view.frame = CGRect(x: 0, y: 0, width: view.frame.width, height: 182)
        pageControll.frame = CGRect(x: 0, y: view.frame.height - 16, width: view.frame.width, height: 16)
        scrollView.frame = CGRect(x: 16, y: 16, width: view.frame.width-32, height: 150)
        
        if (scrollView.subviews.count == 1){
            configureScrollView(bannersList: banners)
        }
    }
    
    func configureScrollView(bannersList: [EntityBannerData]){
        scrollView.contentSize = CGSize(width: scrollView.frame.width * 3, height: scrollView.frame.height)
        scrollView.isPagingEnabled = true
        
        for i in 0..<bannersList.count{
            let page = UIImageView(frame: CGRect(x: CGFloat(i) * scrollView.frame.size.width, y: 0, width: scrollView.frame.size.width, height: scrollView.frame.size.height))
            page.backgroundColor = black60
            page.contentMode = .scaleAspectFill
        
            scrollView.addSubview(page)
            let image = bannersList[i].image
            if( image is ImageDescUrl){
                page.load(url: URL(string:(image as! ImageDescUrl).url)!)
            }
            let identifier = UIAction.Identifier("ButtonBinding.onPressed")
            let action = UIAction(
                identifier: identifier,
                handler: { _ in  }
            )
            page.setOnClickListener(action: {
                bannersList[i].onClick()
                
            })
          
        }
    }
}

extension MyBannersController: UIScrollViewDelegate{
    public func scrollViewDidScroll(_ scrollView: UIScrollView) {
        pageControll.currentPage = Int(floorf(Float(scrollView.contentOffset.x) / Float(scrollView.frame.width)))
    }
}


class MyCustomView : UIView{
        override func sizeThatFits(_ size: CGSize) -> CGSize {
            let originalSize = super.sizeThatFits(size)
            return CGSize(width: size.width, height: originalSize.height)
        }
}

class MyBannersView: UIStackView{
    override func sizeThatFits(_ size: CGSize) -> CGSize {
        let childSize = subviews.first?.sizeThatFits(size)
        return CGSize(width: size.width, height: 182)
    }
}

