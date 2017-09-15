//
//  User.swift
//  Chief
//
//  Created by Sitora on 15.09.17.
//  Copyright © 2017 myapps. All rights reserved.
//

import Foundation
import SwiftyJSON

class User {
    var id : Int32
    var userName : String
    var profileImage : String
    
    required init (json : JSON) {
        id  = json["user_id"].int32Value
        userName = json["user_name"].stringValue
        profileImage = json["profilePicture"].stringValue
        
    }
    
    init() {
        id = Int32()
        userName = String()
        profileImage = String()
    }
    
}
