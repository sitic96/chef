//
//  FirstViewController.swift
//  Chief
//
//  Created by Sitora on 04.03.17.
//  Copyright © 2017 myapps. All rights reserved.
//

import UIKit

class FirstViewController: UIViewController, UIPickerViewDataSource, UIPickerViewDelegate {
    
@IBOutlet var ingredientsChooser: UIPickerView!
    var choosedIngredients = [String]()
    let pickerData = ["Соль","Сахар","Вода","Молоко"]
    var choosedValue:String!
    @IBOutlet var myLabel: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        ingredientsChooser.dataSource = self
        ingredientsChooser.delegate = self
    
        // Do any additional setup after loading the view, typically from a nib.
    }
    @IBAction func hjefn(_ sender: Any) {
        choosedValue=myLabel.text
    }
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
            let svc = segue.destination as! SearchResultsController;
            svc.choosedValue = pickerData
    }
    
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 1
    }
    
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        return pickerData.count;
    }
    
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
//        if (!choosedIngredients.contains(pickerData[row])){
//            choosedIngredients.append(pickerData[row])
//            myLabel.text = myLabel.text! + " " + pickerData[row]
//        }
//        myLabel.sizeToFit()
        return pickerData[row]
    }
    
    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        if (!choosedIngredients.contains(pickerData[row])){
            choosedIngredients.append(pickerData[row])
            myLabel.text = myLabel.text! + " " + pickerData[row]
        }
        myLabel.sizeToFit()
    }
}
