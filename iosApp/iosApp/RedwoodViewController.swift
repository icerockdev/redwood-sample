//
//  RedwoodViewController.swift
//  iosApp
//
//  Created by Aleksey Mikhailov on 24.12.2022.
//  Copyright © 2022 IceRock Development. All rights reserved.
//

import UIKit
import shared_ios

class RedwoodViewController: UIViewController {
    private var displayLink: CADisplayLink!
    private var delegate: RedwoodViewControllerDelegate!
    private let widgetFactory = IosWidgetFactory()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        let container = UIStackView()
        container.backgroundColor = .white
        container.axis = .vertical
        container.alignment = .fill
        container.distribution = .fill
        container.translatesAutoresizingMaskIntoConstraints = false

        view.addSubview(container)
        container.leadingAnchor.constraint(equalTo: view.safeAreaLayoutGuide.leadingAnchor).isActive = true
        container.topAnchor.constraint(equalTo: view.safeAreaLayoutGuide.topAnchor).isActive = true
        container.widthAnchor.constraint(equalTo: view.safeAreaLayoutGuide.widthAnchor).isActive = true

        let delegate = RedwoodViewControllerDelegate(
            root: container,
            widgetFactory: widgetFactory
        )
        self.delegate = delegate
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
