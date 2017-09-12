//
//  RegistrationViewController.swift
//  Chief
//
//  Created by Sitora on 09.09.17.
//  Copyright © 2017 myapps. All rights reserved.
//

import UIKit

class RegistrationViewController: UIViewController {

    @IBOutlet weak var userLogin: UITextField!
    @IBOutlet weak var userPassword: UITextField!
    @IBOutlet weak var userPasswordConf: UITextField!
    @IBOutlet weak var regButton: UIButton!
    @IBOutlet weak var errorMessageLabel: UILabel!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

    @IBAction func registrateButtonClicked(_ sender: Any) {
        guard let user_login = userLogin.text, !user_login.isEmpty else {
            return self.printErrorMessage(message: "Empty login")
        }
        
        guard let password = userPassword.text, !password.isEmpty else {
            return self.printErrorMessage(message: "Empty password")
        }
        
        guard let repeat_password = userPasswordConf.text, !repeat_password.isEmpty else {
            return self.printErrorMessage(message: "Empty password confirmation")
        }
        
        guard password == repeat_password else {
            return self.printErrorMessage(message: "Password confirmation doesn't match Password")
        }
        
        RestApiManager.sharedInstance.signup(login: user_login, password: password){
            responseObject, message in
            if(!responseObject){
                self.printErrorMessage(message: message!)
            }
        }
    }
    
    func printErrorMessage(message : String){
        self.errorMessageLabel.text = message;
        errorMessageLabel.sizeToFit()
    }
}
