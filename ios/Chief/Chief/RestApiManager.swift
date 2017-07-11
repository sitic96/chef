//
//  RestApiManager.swift
//  Chief
//
//  Created by Sitora on 04.03.17.
//  Copyright © 2017 myapps. All rights reserved.
//

import Foundation
import SwiftyJSON

typealias ServiceResponse = (JSON, NSError?) -> Void

class RestApiManager: NSObject {
    static let sharedInstance = RestApiManager()
    
    let URL = "http://MacBook-Pro-Sitora.local:8181/rest/"
    let baseURL = "http://MacBook-Pro-Sitora.local:8181/rest/recipes/28"
    
    func getAllIngredients(onCompletion: @escaping (JSON) -> Void){
        let route = URL + "ingredient/all"
        makeHTTPGetRequest(path: route, onCompletion: { json, err in
            onCompletion(json as JSON)
        })
    }
    
    func getRandomUser(onCompletion: @escaping (JSON) -> Void) {
        let route = baseURL
        makeHTTPGetRequest(path: route, onCompletion: { json, err in
            onCompletion(json as JSON)
        })
    }
    
    func getRandomRecipes(onCompletion: @escaping (JSON) -> Void) {
        let route = URL + "recipes/random"
        makeHTTPGetRequest(path: route, onCompletion: { json, err in
            onCompletion(json as JSON)
        })
    }
    
    
    // MARK: Perform a GET Request
    private func makeHTTPGetRequest(path: String, onCompletion: @escaping ServiceResponse) {
        let request = NSMutableURLRequest(url: NSURL(string: path)! as URL)
        
        let session = URLSession.shared
        
        let task = session.dataTask(with: request as URLRequest, completionHandler: {data, response, error -> Void in
            if let jsonData = data {
                let json:JSON = JSON(data: jsonData)
                onCompletion(json, error as NSError?)
            } else {
                onCompletion(nil, error as NSError?)
            }
        })
        task.resume()
    }
    
    // MARK: Perform a POST Request
    private func makeHTTPPostRequest(path: String, body: [String: AnyObject], onCompletion: @escaping ServiceResponse) {
        let request = NSMutableURLRequest(url: NSURL(string: path)! as URL)
        
        // Set the method to POST
        request.httpMethod = "POST"
        
        do {
            // Set the POST body for the request
            let jsonBody = try JSONSerialization.data(withJSONObject: body, options: .prettyPrinted)
            request.httpBody = jsonBody
            let session = URLSession.shared
            
            let task = session.dataTask(with: request as URLRequest, completionHandler: {data, response, error -> Void in
                if let jsonData = data {
                    let json:JSON = JSON(data: jsonData)
                    onCompletion(json, nil)
                } else {
                    onCompletion(nil, error as NSError?)
                }
            })
            task.resume()
        } catch {
            // Create your personal error
            onCompletion(nil, nil)
        }
    }
}
