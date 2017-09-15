//
//  ProfileViewController.swift
//  Chief
//
//  Created by Sitora on 14.09.17.
//  Copyright © 2017 myapps. All rights reserved.
//

import UIKit
import Locksmith
import SwiftyJSON

class ProfileViewController: UIViewController {

    var user: User!
    @IBOutlet weak var avatar: UIImageView!
    @IBOutlet weak var userName: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        getUserInfo()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func getUserInfo() {
        RestApiManager.sharedInstance.getUser { (json: JSON) in
            DispatchQueue.main.async(execute: {
                self.user = User(json:json)
                self.userName.text = self.user.userName
                self.setProfileImage(link: self.user.profileImage)
            })
        }
    }
    
    func setProfileImage(link : String!) {
        if link != nil {
            let url = URL(string : link)
            let data = try? Data(contentsOf: url!)
            OperationQueue.main.addOperation {
                self.avatar.layer.cornerRadius = 20;
                self.avatar.layer.masksToBounds = true;
                self.avatar.image = UIImage(data: data! as Data)
            }
        }
    }
    @IBAction func logOut(_ sender: Any) {
        do {
            try Locksmith.deleteDataForUserAccount(userAccount: "myUserAccount")
            self.performSegue(withIdentifier: "logOut", sender: self)
        } catch {
            
        }
    }

}
