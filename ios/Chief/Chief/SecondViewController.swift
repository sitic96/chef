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
  //  var tableView: UITableView!
    var hidden:Bool?=false
    @IBOutlet var stackView: UIStackView!
   // @IBOutlet var textLabel: UILabel!
    @IBOutlet var textView: UITextView!
    @IBOutlet var ingredientsLabel: UILabel!
    @IBOutlet var contentView: UIView!
    @IBOutlet var scrollView: UIScrollView!
    @IBOutlet var nameLabel: UILabel!
    @IBOutlet var tableView: UITableView!
    
    @IBOutlet var showHideButton: UIButton!
    
    var recipe :Recipe!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.ingredientsLabel.numberOfLines=0;
        self.automaticallyAdjustsScrollViewInsets = false
//        self.textLabel.lineBreakMode = NSLineBreakMode.byWordWrapping
//        self.textLabel.adjustsFontSizeToFitWidth = false;
        scrollView.contentSize = contentView.frame.size;
        scrollView.delegate = self
        // Do any additional setup after loading the view, typically from a nib.
    }
    @IBAction func btnClicked(_ sender: Any) {
        RestApiManager.sharedInstance.getRandomUser { (json: JSON) in
            self.recipe = Recipe(json: json)
            DispatchQueue.main.async(execute: {
                self.tableView.reloadData()
                self.tableView.rowHeight = UITableViewAutomaticDimension;
            })
        }
        self.nameLabel.text=self.recipe.name;
        self.nameLabel.sizeToFit()
//        self.textLabel.lineBreakMode = NSLineBreakMode.byWordWrapping
//        self.textLabel.numberOfLines=0;
//        self.textLabel.text = self.recipe.text
        for ingredient in recipe.ingredients{
            self.ingredientsLabel.text=self.ingredientsLabel.text! + "\r\n"+ingredient.name
        }
        self.ingredientsLabel.sizeToFit()
        self.textView.isEditable=false
        self.textView.text=recipe.text
        let fixedWidth = textView.frame.size.width
        textView.sizeThatFits(CGSize(width: fixedWidth, height: CGFloat.greatestFiniteMagnitude))
        let newSize = textView.sizeThatFits(CGSize(width: fixedWidth, height: CGFloat.greatestFiniteMagnitude))
        var newFrame = textView.frame
        newFrame.size = CGSize(width: max(newSize.width, fixedWidth), height: newSize.height)
        textView.frame = newFrame;
//        self.textLabel.sizeToFit()
    }
    @IBAction func hideShowButtonClicked(_ sender: UIButton) {
        if hidden!{
            ingredientsLabel.isHidden=false
            hidden=false
        }
        else{
            ingredientsLabel.isHidden=true
            hidden=true
        }
    }
    override func viewWillAppear(_ animated: Bool) {
        recipe = Recipe()
        let frame:CGRect = CGRect(x: 0, y: 150, width: 250, height: 300)
        self.tableView = UITableView(frame: frame)
        tableView.dataSource = self
        tableView.rowHeight = UITableViewAutomaticDimension;
        tableView.delegate = self
        
        showHideButton.setTitleColor(UIColor.black, for: .normal)
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

