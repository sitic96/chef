//
//  MyTable.swift
//  
//
//  Created by Sitora on 12.03.17.
//
//

import Foundation
class MyTable: UITableView, UITableViewDataSource {
    let data = ["January","February","March","April","May","June","July","August","September","October","November","December"]
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return data.count
    }
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCellWithIdentifier("SauceCell", forIndexPath: indexPath)
        cell.textLabel?.text = data[indexPath.row]
        return cell
    }
}
