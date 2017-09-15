//
//  SecondViewController.swift
//  Chief
//
//  Created by Sitora on 04.03.17.
//  Copyright © 2017 myapps. All rights reserved.
//
import UIKit
import CoreData
import SwiftyJSON

class SecondViewController:UIViewController {
    var hidden:Bool?=false
    
    @IBOutlet var scroll: UIScrollView!
    @IBOutlet weak var nameLabel: UILabel!
    @IBOutlet weak var showHideButton: UIButton!
    @IBOutlet weak var tableView: UITableView!
    
    var recipe :Recipe!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.tableView.estimatedRowHeight = 50
        self.tableView.rowHeight = UITableViewAutomaticDimension
    }
    
    @IBAction func btnClicked(_ sender: Any) {

    }
    @IBAction func hideShowButtonClicked(_ sender: UIButton) {
        tableView.isHidden = !tableView.isHidden
        checkScrollSize();
    }
    override func viewWillAppear(_ animated: Bool) {
        recipe = Recipe()
        //let frame:CGRect = CGRect(x: 0, y: 150, width: 250, height: 300)
        showHideButton.setTitleColor(UIColor.black, for: .normal)
    }
    
    func checkScrollSize(){
        var contentRect = CGRect.zero
        for view in self.scroll.subviews {
            contentRect = contentRect.union(view.frame)
        }
        self.scroll.contentSize = contentRect.size
    }
    
}

extension SecondViewController: UITableViewDataSource, UITableViewDelegate {
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        guard self.recipe != nil else { return 0 }
        
        return self.recipe.ingredients.count;
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "cell", for: indexPath)
        
        let user = self.recipe.ingredients[indexPath.row]
        
        cell.textLabel?.text = user.name
        return cell
    }
    
}
