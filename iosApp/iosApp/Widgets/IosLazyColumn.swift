//
//  IosLazyColumn.swift
//  iosApp
//
//  Created by alobynya on 20.01.2023.
//  Copyright © 2023 IceRock Development. All rights reserved.
//

import UIKit
import shared_ios

class IosLazyColumn: MySuperLazyColumn {
    
    
    private let root: UITableView = {
        let container = UITableView()
        container.translatesAutoresizingMaskIntoConstraints = true
        container.rowHeight = UITableView.automaticDimension
        container.estimatedRowHeight = UITableView.automaticDimension
        container.allowsSelection = false
        return container;
    }()
    
    private let dataSource: CompouseDataSource
    
    init(){
        dataSource = CompouseDataSource (tableView: root) {
            (tableView: UITableView, indexPath: IndexPath, itemIdentifier: UInt) -> UITableViewCell? in
            tableView.dequeueReusableCell(withIdentifier: indexPath.description)
        }
    }
    
    
    var counter = 1
    
    func childs(childs: [Redwood_widgetWidgetChildren]) {
        counter = counter + 1
    }
    
    func childs2(childs: [@convention(block) () -> KotlinUnit?]) {
        counter = counter + 2
   }
    
    
    var testChild: Redwood_widgetWidgetChildren {
        ExposedKt.createViewChildrenListener(parent: root, insert: myInsert)
    }

    func myInsert(view: UIView,index: KotlinInt){
        var data: [UIView] = dataSource.data
        data.insert(view, at: 0)
        dataSource.setData(newList:data)
    }
    
    var layoutModifiers: Redwood_runtimeLayoutModifier = ExposedKt.layoutModifier()
 
    var value: Any { root }
    
    
}

class CompouseDataSource: UITableViewDiffableDataSource<UInt, UInt>{
    
     var data: [UIView] = []
    
    func setData(newList: [UIView]){
        data = newList
    }
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return data.count
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let view = data[indexPath.first ?? 0]
        let index = indexPath.first ?? 0
        var cell =  tableView.dequeueReusableCell(withIdentifier: indexPath.description)
        cell?.insertSubview(view, at: 0)
        return cell!
    }
}
