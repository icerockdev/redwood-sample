//
//  KeyboardAwareAnimatedConstraint.swift
//  iosApp
//
//  Created by alobynya on 14.02.2023.
//  Copyright © 2023 IceRock Development. All rights reserved.
//

import UIKit

class KeyboardAwareAnimatedConstraint: NSLayoutConstraint {
    private var defaultConst: CGFloat = 0
    private var bottomPadding: CGFloat = 0
    private var isBottomPaddingCalculated = false

    override func awakeFromNib() {
        super.awakeFromNib()

        setupKeyboardObservers()
        defaultConst = constant
    }
    
    deinit {
        removeKeyboardObservers()
    }
    
    private func setupKeyboardObservers() {
        NotificationCenter.default.addObserver(
            self,
            selector: #selector(keyboardWillShow(notification:)),
            name: UIResponder.keyboardWillShowNotification,
            object: nil
        )
        
        NotificationCenter.default.addObserver(
            self,
            selector: #selector(keyboardWillHide(notification:)),
            name: UIResponder.keyboardWillHideNotification,
            object: nil
        )
    }
    
    private func removeKeyboardObservers() {
        NotificationCenter.default.removeObserver(
            self,
            name: UIResponder.keyboardWillShowNotification,
            object: nil
        )
        
        NotificationCenter.default.removeObserver(
            self,
            name: UIResponder.keyboardWillHideNotification,
            object: nil
        )
    }
    
    @objc
    dynamic func keyboardWillShow(
        notification: Notification
    ) {
        if let secondItem = secondItem,
           let widowOptional = secondItem.window,
           widowOptional == nil, firstItem?.window == nil {
            return
        }

        if !isBottomPaddingCalculated {
            let window = UIApplication.shared.windows.first!
            let view = secondItem as? UIView ?? UIView()
            let frame = view.superview?.convert(view.frame, to: window) ?? .zero

            bottomPadding = window.bounds.height - frame.maxY
            isBottomPaddingCalculated = true
        }

        animateWithKeyboard(notification: notification) { [weak self] keyboardFrame in
            guard let self = self else {
                return
            }

            self.constant = self.defaultConst + keyboardFrame.height - self.bottomPadding
        }
    }

    @objc
    dynamic func keyboardWillHide(
        notification: Notification
    ) {
        animateWithKeyboard(notification: notification) { [weak self] _ in
            self?.constant = self?.defaultConst ?? 0
        }
    }

    private func animateWithKeyboard(
        notification: Notification,
        animations: ((_ keyboardFrame: CGRect) -> Void)?
    ) {
        let durationKey = UIResponder.keyboardAnimationDurationUserInfoKey
        let duration = notification.userInfo?[durationKey] as? Double ?? 0

        let frameKey = UIResponder.keyboardFrameEndUserInfoKey
        let keyboardFrameValue = notification.userInfo?[frameKey] as? NSValue

        if duration == 0 {
            animations?(keyboardFrameValue?.cgRectValue ?? .zero)
            return
        }

        let curveKey = UIResponder.keyboardAnimationCurveUserInfoKey
        let curveValue = notification.userInfo?[curveKey] as? Int ?? 0
        let curve = UIView.AnimationCurve(rawValue: curveValue) ?? .easeOut

        let animator = UIViewPropertyAnimator(
            duration: duration,
            curve: curve
        ) {
            animations?(keyboardFrameValue?.cgRectValue ?? .zero)

            (self.secondItem as? UIView)?.superview?.layoutIfNeeded()
            (self.firstItem as? UIView)?.superview?.layoutIfNeeded()
            (self.secondItem as? UIView)?.layoutIfNeeded()
            (self.firstItem as? UIView)?.layoutIfNeeded()
        }

        animator.startAnimation()
    }
}
