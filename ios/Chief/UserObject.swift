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
    var pictureURL: String!
    var username: String!
    
    required init(json: JSON) {
        pictureURL = json["picture"]["medium"].stringValue
        username = json["email"].stringValue
    }
}
