//
//  SecondViewController.swift
//  Chief
//
//  Created by Sitora on 04.03.17.
//  Copyright © 2017 myapps. All rights reserved.
//
import UIKit
import SwiftyJSON

class SecondViewController:UIViewController, UITableViewDataSource, UITableViewDelegate {
    
    @IBOutlet
    var nameLabel: UILabel!
    @IBOutlet
    var tableView: UITableView!
   // var items = [Recipe]()
    var recipe :Recipe!
    
    override func viewWillAppear(_ animated: Bool) {
        recipe = Recipe()
        let frame:CGRect = CGRect(x: 0, y: 100, width: self.view.frame.width, height: self.view.frame.height-100)
        self.tableView = UITableView(frame: frame)
        self.tableView.dataSource = self
        self.tableView.delegate = self
        self.view.addSubview(self.tableView)
        
        let btn = UIButton(frame: CGRect(x: 0, y: 25, width: self.view.frame.width, height: 50))
        btn.backgroundColor = UIColor.cyan
        btn.setTitle("Add new Dummy", for: UIControlState.normal)
        btn.addTarget(self, action: #selector(SecondViewController.addDummyData), for: UIControlEvents.touchUpInside)
        self.view.addSubview(btn)
    }
    
    func addDummyData() {
        RestApiManager.sharedInstance.getRandomUser { (json: JSON) in

            self.recipe = Recipe(json: json)
            self.nameLabel.text=self.recipe.name;
                DispatchQueue.main.async(execute: {
                    self.tableView.reloadData()
                })
        }
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.recipe.ingredients.count;
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        var cell = tableView.dequeueReusableCell(withIdentifier: "CELL")
        
        if cell == nil {
            cell = UITableViewCell(style: UITableViewCellStyle.value1, reuseIdentifier: "CELL")
        }
        let user = self.recipe.ingredients[indexPath.row]
        
        if let url = NSURL(string: user.name) {
            if let data = NSData(contentsOf: url as URL) {
                cell?.imageView?.image = UIImage(data: data as Data)
            }
        }
        cell!.textLabel?.text = user.name
        return cell!
    }
}

