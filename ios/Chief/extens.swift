//
//  extens.swift
//  Chief
//
//  Created by Sitora on 12.03.17.
//  Copyright © 2017 myapps. All rights reserved.
//

import Foundation
extension UIStackView {
    
    convenience init(axis:UILayoutConstraintAxis, spacing:CGFloat) {
        self.init()
        self.axis = axis
        self.spacing = spacing
        self.translatesAutoresizingMaskIntoConstraints = false
    }
    
    func anchorStackView(toView view:UIView, anchorX:NSLayoutXAxisAnchor, equalAnchorX:NSLayoutXAxisAnchor, anchorY:NSLayoutYAxisAnchor, equalAnchorY:NSLayoutYAxisAnchor) {
        view.addSubview(self)
        anchorX.constraintEqualToAnchor(equalAnchorX).active = true
        anchorY.constraintEqualToAnchor(equalAnchorY).active = true
        
    }
    
}
