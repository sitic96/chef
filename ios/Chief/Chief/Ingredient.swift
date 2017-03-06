//
//  Ingredient.swift
//  Chief
//
//  Created by Sitora on 06.03.17.
//  Copyright © 2017 myapps. All rights reserved.
//

import Foundation
import SwiftyJSON

class Ingredient {
    var id: Int16
    var name :String!
    
    
    required init(json: JSON) {
        id = json["id"].int16Value
        name = json["name"].stringValue
    }
}
