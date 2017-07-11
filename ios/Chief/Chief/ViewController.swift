//
//  ViewController.swift
//  Chief
//
//  Created by Sitora on 11.07.17.
//  Copyright © 2017 myapps. All rights reserved.
//

import UIKit
import Foundation
class ViewController: UIViewController, UICollectionViewDataSource, UICollectionViewDelegate {
    
    //var items = [Item]()
    var tableData: [String] = ["Evo X", "458", "GTR", "Evo X", "458", "GTR", "Evo X", "458", "GTR", "Evo X", "458", "GTR"]
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return tableData.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell: colvwCell = collectionView.dequeueReusableCell(withReuseIdentifier: "Cell", for: indexPath) as! colvwCell
        cell.label.text = tableData[indexPath.row]
        return cell
    }
    
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        print("Cell \(indexPath.row) selected")
    }
    
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    
    
}
