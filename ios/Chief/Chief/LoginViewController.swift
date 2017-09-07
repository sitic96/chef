//
//  LoginViewController.swift
//  Chief
//
//  Created by Sitora on 06.09.17.
//  Copyright © 2017 myapps. All rights reserved.
//

import UIKit

class LoginViewController: UIViewController {

    @IBOutlet weak var userLogin: UITextField!
    @IBOutlet weak var userPassword: UITextField!
    @IBOutlet weak var loginButton: UIButton!
    @IBOutlet weak var errorMessage: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBAction func loginClick(_ sender: Any) {
        guard let user_login = userLogin.text, !user_login.isEmpty else {
            return self.printErrorMessage(message: "Empty login")
        }
        guard let password = userPassword.text, !password.isEmpty else {
            return self.printErrorMessage(message: "Empty password")
        }
        
        RestApiManager.sharedInstance.login(login: user_login, password: password)
}
    func printErrorMessage(message : String){
        self.errorMessage.text = message;
}
}
