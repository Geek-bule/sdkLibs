//
//  ViewController.m
//  DGServerManager
//
//  Created by apple on 2018/3/22.
//  Copyright © 2018年 apple. All rights reserved.
//

#import "ViewController.h"
#import "MemberModel.h"
#import "TanTableViewCell.h"
#import "ServerManager.h"
#import "PushDataController.h"
#import "StaticticsController.h"
#import "AppDelegate.h"

@interface ViewController ()<UITableViewDataSource, UITabBarDelegate, TanTableViewCellDelegate>
//@property (nonatomic, strong) NSMutableArray *dataArr; //模型数据集

@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view, typically from a nib.
    
    self.tablView.dataSource = self;
    self.tablView.rowHeight = 50.f; //设置行高
    self.tablView.separatorStyle = UITableViewCellSeparatorStyleNone; //隐藏自带的分割线
    
    //启用下拉刷新控件
    self.refresh = [[UIRefreshControl alloc] init];
    self.refresh.attributedTitle = [[NSAttributedString alloc] initWithString:@"下拉刷新^_^"];
    [self.refresh addTarget:self action:@selector(refreshTableView:) forControlEvents:UIControlEventValueChanged];
    self.tablView.refreshControl = self.refresh;
    
    AppDelegate *delegate = (AppDelegate *)[UIApplication sharedApplication].delegate;
    UINavigationController *navCtrlr = [[UINavigationController alloc]initWithRootViewController:self];
    [delegate.window setRootViewController:navCtrlr];
    
    [self.refresh beginRefreshing];
    [self refreshTableView:nil];
}

-(void) viewWillAppear:(BOOL)animated {
   
}

#pragma mark - 自定义事件
- (void)refreshTableView: (UIRefreshControl *)sender{
    
    //拉取服务器数据
    [[ServerManager shareServer] pullGameInfo:^(BOOL success) {
        if (success) {
            [self.refresh endRefreshing]; //停止刷新
            [self.tablView reloadData]; //加载
        }else{
            [self.refresh endRefreshing]; //停止刷新
        }
    }];
    
    
    NSDateFormatter *formatter = [[NSDateFormatter alloc] init];
    [formatter setDateFormat:@"YYYYMMdd"];
    NSDate *datenow = [NSDate date];
    NSString *currentTimeString = [formatter stringFromDate:datenow];
    [[ServerManager shareServer] startGetStatictics:currentTimeString back:^(BOOL success) {
        
    }];
}

#pragma mark - UITableViewDataSource
- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return [ServerManager shareServer].dataDoc.allKeys.count;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    TanTableViewCell *cell = [TanTableViewCell cellWithTableView:tableView];
    cell.delegate = self;
    
    GameInfo *info = [[ServerManager shareServer].dataDoc objectForKey:[NSNumber numberWithInteger:indexPath.row+1]];
    MemberModel *model = [MemberModel memberWithID:[info.id stringValue] gameName:info.name gameCode:info.code mobileType:info.mobileType];
    [cell setData:model];
    
    return cell;
}

#pragma mark - cell代理方法
//删除单元格
- (void)deleteMember:(TanTableViewCell *)cell{
    NSIndexPath *path = [self.tablView indexPathForCell:cell]; //获取cell所在位置
    //删除数组中数据
   // [self.dataDoc removeObjectForKey:self.dataDoc.allKeys[path.row]];
    //删除单元格
    [self.tablView deleteRowsAtIndexPaths:@[path] withRowAnimation:UITableViewRowAnimationLeft];
}

//关闭其他cell的左滑
- (void)closeOtherCellLeftSwipe{
    //循环显示的cell
    for (TanTableViewCell *item in self.tablView.visibleCells) {
        [item closeSwipe];
    }
}

- (void)editerMember:(TanTableViewCell *)cell
{
    [cell closeSwipe];
    UIStoryboard *storyBoard = [UIStoryboard storyboardWithName:@"Main" bundle:nil];
    PushDataController *pushData = [storyBoard instantiateViewControllerWithIdentifier:@"PushDataController"];
    [pushData initWithCode: cell.model.gameCode];
    [self.navigationController pushViewController:pushData animated:YES];
}

- (void)clickMember:(TanTableViewCell *)cell
{
    UIStoryboard *storyBoard = [UIStoryboard storyboardWithName:@"Main" bundle:nil];
    StaticticsController *statictics = [storyBoard instantiateViewControllerWithIdentifier:@"StaticticsController"];
    [statictics initWithCode:cell.model.gameId];
    [self.navigationController pushViewController:statictics animated:YES];
}


- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


@end
