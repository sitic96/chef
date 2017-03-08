//
//  Recipe.swift
//  Chief
//
//  Created by Sitora on 04.03.17.
//  Copyright © 2017 myapps. All rights reserved.
//

import Foundation
import SwiftyJSON

class Recipe {
    var ingredients = [Ingredient]()
    var category: Int16
    var id: Int16
    var name :String!
    var text :String!
    
    
    required init(json: JSON) {
        if let results = json["ingredients"].array {
            for entry in results {
                self.ingredients.append(Ingredient(json: entry))
            }
        }
        
        category = json["category"].int16Value
        id = json["id"].int16Value
        name = json["name"].stringValue
        text = json["text"].stringValue
    }
    
    init() {
        category = Int16()
        id = Int16()
        name = String()
        text=String()
        ingredients = [Ingredient]()
    }
}
