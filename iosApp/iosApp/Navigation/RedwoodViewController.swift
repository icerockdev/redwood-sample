//
//  RedwoodViewController.swift
//  iosApp
//
//  Created by Aleksey Mikhailov on 13.02.2023.
//  Copyright © 2023 IceRock Development. All rights reserved.
//

import UIKit
import shared_ios

class RedwoodViewController: UIViewController {
    private var displayLink: CADisplayLink!
    private var delegate: NavigationRedwoodViewControllerDelegate!
    private let delegateBuilder: (UIStackView) -> NavigationRedwoodViewControllerDelegate
    
    init(
        delegateBuilder: @escaping (UIStackView) -> NavigationRedwoodViewControllerDelegate
    ) {
        self.delegateBuilder = delegateBuilder
        
        super.init(nibName: nil, bundle: nil)
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        let container = UIStackView()
        container.backgroundColor = .white
        container.axis = .vertical
        container.alignment = .fill
        container.distribution = .fill
        container.translatesAutoresizingMaskIntoConstraints = false

        view.addSubview(container)
        container.leadingAnchor.constraint(equalTo: view.leadingAnchor).isActive = true
        container.topAnchor.constraint(equalTo: view.topAnchor).isActive = true
        container.widthAnchor.constraint(equalTo: view.widthAnchor).isActive = true
        container.heightAnchor.constraint(equalTo: view.heightAnchor).isActive = true

        self.delegate = delegateBuilder(container)
    }
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        
        let displayLink = CADisplayLink.init(target: self, selector: #selector(tickClock))
        displayLink.add(to: .current, forMode: .default)
        self.displayLink = displayLink
    }

    @objc func tickClock() {
        delegate.tickClock()
    }

    override func viewDidDisappear(_ animated: Bool) {
        super.viewDidDisappear(animated)
        
        displayLink.invalidate()
    }

    deinit {
        delegate.dispose()
    }
}
