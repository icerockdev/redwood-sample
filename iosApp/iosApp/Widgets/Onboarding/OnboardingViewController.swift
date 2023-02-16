//
//  OnboardingViewController.swift
//  iosApp
//
//  Created by alobynya on 15.02.2023.
//  Copyright © 2023 IceRock Development. All rights reserved.
//

import UIKit
import shared_ios

class OnboardingViewController: UIViewController {
    
    private let scrollView : UIScrollView =  {
        let scrollView = UIScrollView()
        scrollView.layer.cornerRadius = 16
        scrollView.clipsToBounds = true
        scrollView.showsHorizontalScrollIndicator = false
        return scrollView
    }()
    
    private let pageControll : UIPageControl = {
        let pageControll = UIPageControl()
        pageControll.pageIndicatorTintColor = black60
        pageControll.currentPageIndicatorTintColor = primaryColor
        return pageControll
    }()
    
    private let nextButton: FillButton = {
        var configuration = UIButton.Configuration.filled()
        configuration.imagePadding = 10
        configuration.contentInsets = NSDirectionalEdgeInsets(top: 12, leading: 20, bottom: 12, trailing: 20)
        let view = FillButton(configuration: configuration)
        view.translatesAutoresizingMaskIntoConstraints = true
        view.clipsToBounds = true
        view.widgetWidth = EntitySize.Fill()
        view.setTitle("Далее", for: .normal)
        // todo to func
        view.backgroundColor = primaryColor
        view.layer.cornerRadius = 10
        view.configuration?.baseBackgroundColor = UIColor.clear
        view.isHighlighted = false
        view.setTitleColor(.white, for: UIControl.State.normal)
        view.setTitleColor(.white, for: UIControl.State.disabled)
        view.tintColor = .white
        return view
    }()
    
    private let closeButton: UIButton = {
        var configuration = UIButton.Configuration.filled()
        configuration.imagePadding = 10
        configuration.contentInsets = NSDirectionalEdgeInsets(top: 12, leading: 12, bottom: 12, trailing: 12)
        let view = UIButton(configuration: configuration)
        view.translatesAutoresizingMaskIntoConstraints = true
        view.configuration?.baseBackgroundColor = UIColor.clear
        view.backgroundColor = UIColor.clear
          view.clipsToBounds = true
        view.setImage(UIImage(named: "cross"), for: .normal)
        return view
    }()
    
    private var banners: [UIView] = []
    private var onFinishClick: (() -> Void)? = nil
    
    func setOnFinishClick(onFinishClick: (() -> Void)? = nil) {
        let identifier = UIAction.Identifier("ButtonBinding.onPressed")
        let closeAction = UIAction(
            identifier: identifier,
            handler: { _ in onFinishClick?() }
        )
        let nextAction = UIAction(
            identifier: identifier,
            handler: { [self] _ in
                if(pageControll.currentPage == self.banners.count - 1){
                    onFinishClick?()
                }else{
                    scrollView.setContentOffset(CGPoint(x:CGFloat(pageControll.currentPage + 1) * (view.frame.size.width-32), y:0), animated: true)
                }
            }
        )

        closeButton.removeAction(identifiedBy: identifier, for: .touchUpInside)
        closeButton.addAction(closeAction, for: .touchUpInside)
        nextButton.removeAction(identifiedBy: identifier, for: .touchUpInside)
        nextButton.addAction(nextAction, for: .touchUpInside)
  
    }
    
    func addPage(view: UIView,index: KotlinInt){
       banners.append(view)
        if(view.frame.size.width != 0){
            configureScrollView(bannersList: banners)
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
        view.addSubview(closeButton)
        view.addSubview(nextButton)
    }
    
    @objc private func pageControlDidChange(_ sender: UIPageControl){
        let current = sender.currentPage
        scrollView.setContentOffset(CGPoint(x:CGFloat(current) * (view.frame.size.width-32), y:0), animated: true)
    }
    
    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()
        //todo fix
        view.frame = CGRect(x: 0, y: 0, width: view.frame.width, height: view.frame.height)
        let nextButtonSize = nextButton.sizeThatFits(view.frame.size)
        pageControll.frame = CGRect(x: 0, y: view.frame.height - 48 - nextButtonSize.height, width: view.frame.width, height: 16)
        scrollView.frame = CGRect(x: 0, y: 0, width: view.frame.width, height: view.frame.height - 48 - nextButtonSize.height)
        nextButton.frame = CGRect(x: 16, y: view.frame.height - nextButtonSize.height - 16,
                                  width: view.frame.width-32, height: nextButtonSize.height)
        closeButton.frame = CGRect(x: view.frame.width-56, y: 16, width: 40, height: 40)
        if (scrollView.subviews.count == 1){
            configureScrollView(bannersList: banners)
        }
    }
    
    func configureScrollView(bannersList: [UIView]){
        pageControll.numberOfPages = bannersList.count
       scrollView.contentSize = CGSize(width: scrollView.frame.width * CGFloat(bannersList.count), height: scrollView.frame.height)
        scrollView.isPagingEnabled = true
        
        for i in 0..<bannersList.count{
            bannersList[i].frame = CGRect(x: CGFloat(i) * scrollView.frame.size.width, y: 0, width: scrollView.frame.size.width, height: scrollView.frame.size.height)
            scrollView.addSubview(bannersList[i])
           
        }
    }
}

extension OnboardingViewController: UIScrollViewDelegate{
    public func scrollViewDidScroll(_ scrollView: UIScrollView) {
        pageControll.currentPage = Int(floorf(Float(scrollView.contentOffset.x) / Float(scrollView.frame.width)))
    }
}
