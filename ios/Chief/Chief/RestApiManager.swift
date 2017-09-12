//
//  RestApiManager.swift
//  Chief
//
//  Created by Sitora on 04.03.17.
//  Copyright © 2017 myapps. All rights reserved.
//

import Foundation
import SwiftyJSON
import Alamofire

typealias ServiceResponse = (JSON, NSError?) -> Void

class RestApiManager: NSObject {
    static let sharedInstance = RestApiManager()
    
    let URL = "http://MacBook-Pro-Sitora.local:8181/rest/"
    let baseURL = "http://localhost:8080/rest_gif"
    
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
    
    func getUser(onCompletion: @escaping (JSON) -> Void) {
        let route = URL + "recipes/random"
        makeHTTPGetRequest(path: route, onCompletion: { json, err in
            onCompletion(json as JSON)
        })
    }
    
    func getRecipe(onCompletion: @escaping (JSON) -> Void){
        let route = "http://localhost:8080/rest_gif/recipes/complete/byid/2"
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
    
    func login(login:String, password:String, completionHandler: @escaping (Bool) -> ()){
        let parameters: Parameters = [
            "login": login,
            "password": password,
            ]
        
        Alamofire.request(baseURL+"/users/login", method: .post, parameters: parameters, encoding: JSONEncoding.default)
            .responseJSON{
                response in print(response.response as Any)
                var statusCode = (response.response?.statusCode)!
                switch statusCode {
                case 404:
                    completionHandler(false)
                    
                case 200 :
                    completionHandler(true)
                default:
                    completionHandler(false)
                }
                
        }
        
    }
    
    func signup(login:String, password:String,  completionHandler: @escaping (Bool, String?) -> ())  {
        let parameters: Parameters = [
            "login": login,
            "password": password,
            ]
        Alamofire.request(baseURL+"/users/signup", method: .post, parameters: parameters, encoding: JSONEncoding.default)
            .responseJSON{
                response in print(response.response as Any)
                var statusCode = (response.response?.statusCode)!
                switch statusCode {
                case 409:
                    if let data = response.data {
                        let message = String(data: data, encoding: String.Encoding.utf8)
                        completionHandler(false, message)
                    }
                case 200 :
                    completionHandler(true, nil)
                default:
                    completionHandler(false, "Error")
                }
        }
    }
    
    //     MARK: Perform a POST Request
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
