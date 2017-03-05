//
//  UserObject.swift
//  Chief
//
//  Created by Sitora on 04.03.17.
//  Copyright © 2017 myapps. All rights reserved.
//

import Foundation
import SwiftyJSON

class UserObject {
    var category: Int16
    var id: Int16
    var name :String!
    var text :String!
    
    
    required init(json: JSON) {
        category = json["category"].int16Value
        id = json["id"].int16Value
        name = json["name"].stringValue
        text = json["text"].stringValue
    }
}
