//
//  IosWidgetBannerColumn.swift
//  iosApp
//
//  Created by alobynya on 26.02.2023.
//  Copyright © 2023 IceRock Development. All rights reserved.
//

import UIKit
import shared_ios

class IosWidgetBannersColumn : BannersColumn {
   
    private let controller: MyBannersColumnController = {
       let container = MyBannersColumnController()
       return container;
    }()
    
    func bannersList(bannersList_ bannersList: [EntityBannerColumnData]) {
       controller.setBanners(bannersList: bannersList)
    }
    
    var layoutModifiers: Redwood_runtimeLayoutModifier = ExposedKt.layoutModifier()
  
    var value: Any {
        return controller.view
    }
    
}

class MyBannersColumnController: UIViewController {
    
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
    
    private var banners: [EntityBannerColumnData] = []
    
    func setBanners(bannersList: [EntityBannerColumnData]){
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
    
    func configureScrollView(bannersList: [EntityBannerColumnData]){
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
            
            let label = UILabel(frame: CGRect(x: 16, y: 16, width: page.frame.size.width - 32, height: page.frame.size.height - 32))
            page.addSubview(label)
            label.text = action.title
        }
    }
}

extension MyBannersColumnController: UIScrollViewDelegate{
    public func scrollViewDidScroll(_ scrollView: UIScrollView) {
        pageControll.currentPage = Int(floorf(Float(scrollView.contentOffset.x) / Float(scrollView.frame.width)))
    }
}




