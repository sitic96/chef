//
//  FirstViewController.swift
//  Chief
//
//  Created by Sitora on 04.03.17.
//  Copyright © 2017 myapps. All rights reserved.
//

import UIKit
import CoreData
import SwiftyJSON

class FirstViewController: UIViewController, UIPickerViewDataSource, UIPickerViewDelegate {
    
@IBOutlet var ingredientsChooser: UIPickerView!
    var choosedIngredients = [String]()
    var pickerData = [Ingredient]()
    var choosedValue:String!
    @IBOutlet var myLabel: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        getAllIngredients()
        ingredientsChooser.dataSource = self
        ingredientsChooser.delegate = self
        ingredientsChooser.reloadAllComponents()
    }
    @IBAction func hjefn(_ sender: Any) {
        choosedValue=myLabel.text
    }
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    
    func getAllIngredients(){
        RestApiManager.sharedInstance.getAllIngredients { (json: JSON) in
            //let json = JSON(data: json)
            for (_, object) in json {
             self.pickerData.append(Ingredient(json: object))
                //let name = object["name"].stringValue
            }
        }
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
            //let svc = segue.destination as! SearchResultsController;
            //svc.choosedValue = pickerData
    }
    
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 1
    }
    
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        return pickerData.count;
    }
    
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        return pickerData[row].name
    }
    
    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        if (!choosedIngredients.contains(pickerData[row].name)){
            choosedIngredients.append(pickerData[row].name)
            myLabel.text = myLabel.text! + " " + pickerData[row].name
        }
        myLabel.sizeToFit()
    }
}
