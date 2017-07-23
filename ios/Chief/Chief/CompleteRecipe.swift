//
//  CompleteRecipe.swift
//  Chief
//
//  Created by Sitora on 23.07.17.
//  Copyright © 2017 myapps. All rights reserved.
//

import Foundation
import SwiftyJSON

class CompleteRecipe {
    var category: String!
    var recipe_id: Int16
    var img_link :String!
    var profile_picture :String!
    var user_id: Int16
    var recipe_name :String!
    var user_name :String!
    
    
    required init(json: JSON) {
        /*    if let results = json["ingredients"].array {
         for entry in results {
         self.ingredients.append(Ingredient(json: entry))
         }
         }
         */
        
        category = json["category"].stringValue
        recipe_id = json["recipe_id"].int16Value
        img_link = json["img_link"].stringValue
        profile_picture = json["profile_picture"].stringValue
        user_id = json["user_id"].int16Value
        recipe_name = json["recipe_name"].stringValue
        user_name = json["user_name"].stringValue
        
    }
    
    init() {
        category = String()
        recipe_id = Int16()
        img_link = String()
        profile_picture=String()
        user_id = Int16()
        recipe_name = String()
        user_name = String()
    }
}
