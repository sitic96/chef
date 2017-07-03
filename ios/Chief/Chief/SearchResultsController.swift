//
//  SearchResultsController.swift
//  Chief
//
//  Created by Sitora on 21.03.17.
//  Copyright © 2017 myapps. All rights reserved.
//

import UIKit

class SearchResultsController: UIViewController {
     var choosedValue:[Ingredient]!
    @IBOutlet var label: UILabel!
    override func viewDidLoad() {
        super.viewDidLoad()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    

    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
//         Get the new view controller using segue.destinationViewController.
//         Pass the selected object to the new view controller.
    }

}
