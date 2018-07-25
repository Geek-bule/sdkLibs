//
//  PushDataController.m
//  DGServerManager
//
//  Created by apple on 2018/3/22.
//  Copyright © 2018年 apple. All rights reserved.
//

#import "PushDataController.h"
#import "MemberModel.h"
#import "TanTableViewCell.h"
#import "ServerManager.h"
#import "STAlertView.h"

@interface PushDataController ()<UITableViewDataSource, UITabBarDelegate, TanTableViewCellDelegate>
@property (nonatomic, strong) NSString *gameCode; //模型数据集
@property (nonatomic, strong) NSMutableDictionary *gameinfoDoc; //模型数据集
@property (nonatomic, strong) STAlertView *stAlertView;
@end

@implementation PushDataController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    
    self.tablView.dataSource = self;
    self.tablView.rowHeight = 50.f; //设置行高
    self.tablView.separatorStyle = UITableViewCellSeparatorStyleNone; //隐藏自带的分割线
    
    self.gameinfoDoc = [[NSMutableDictionary alloc] init];
}

-(void) viewWillAppear:(BOOL)animated {
    
}

- (void)initWithCode:(NSString*)code
{
    self.gameCode = code;
    
    
    [self loadServerData];
}

- (void)loadServerData
{
    
    [[ServerManager shareServer] startPullPushData:self.gameCode back:^(BOOL success) {
        if (success) {
            for (id key in [ServerManager shareServer].dataDoc) {
                GameInfo *info = [ServerManager shareServer].dataDoc[key];
                if ([info.code isEqualToString: self.gameCode]) {
                    continue;
                }
                [self.gameinfoDoc setObject:info forKey:key];
            }
            NSDictionary *_dic = [ServerManager shareServer].pushDataDoc;
            for (NSString*key in _dic) {
                PushGameInfo *info = _dic[key];
                NSNumber* gameId = [[ServerManager shareServer] getGameIdByCode:info.gameCode];
                [self.gameinfoDoc removeObjectForKey:gameId];
            }
            [self.tablView reloadData]; //加载
        }else{
            
        }
    }];
}

#pragma mark - UITableViewDataSource
- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return [ServerManager shareServer].pushDataDoc.allKeys.count+self.gameinfoDoc.allKeys.count;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    TanTableViewCell *cell = [TanTableViewCell cellWithTableView:tableView];
    cell.delegate = self;
    
    NSInteger count = [ServerManager shareServer].pushDataDoc.allKeys.count;
    if (indexPath.row+1 > count) {
        NSInteger index = indexPath.row-count;
        GameInfo *gameInfo = [self.gameinfoDoc objectForKey:[self.gameinfoDoc allKeys][index]];
        MemberModel *model = [MemberModel memberWithID:@"0" gameName:gameInfo.name gameCode:gameInfo.code mobileType:gameInfo.mobileType];
        [cell setData:model];
    }else{
        NSDictionary *_dic = [ServerManager shareServer].pushDataDoc;
        PushGameInfo *info = [_dic objectForKey:[_dic allKeys][indexPath.row]];
        NSNumber* gameId = [[ServerManager shareServer] getGameIdByCode:info.gameCode];
        GameInfo *gameInfo = [ServerManager shareServer].dataDoc[gameId];
        MemberModel *model = [MemberModel memberWithID:[info.percent stringValue] gameName:gameInfo.name gameCode:gameInfo.code mobileType:gameInfo.mobileType];
        [cell setData:model];
    }
    
    
    
    return cell;
}

#pragma mark - cell代理方法
//删除单元格
- (void)deleteMember:(TanTableViewCell *)cell{
    NSString* pushGameCode = cell.model.gameCode;
    NSNumber *percent = [NSNumber numberWithInteger:0];
    [[ServerManager shareServer] startModifiyPushData:self.gameCode PushGame:pushGameCode perent:percent back:^(BOOL success) {
        if (success) {
            [self loadServerData]; //加载
        }else{
            
        }
    }];
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
    NSString* pushGameCode = cell.model.gameCode;
    self.stAlertView = [[STAlertView alloc] initWithTitle:@"修改推送比例"
                                                  message:nil
                                            textFieldHint:@"请输入纯数字"
                                           textFieldValue:nil
                                        cancelButtonTitle:@"取消"
                                        otherButtonTitles:@"确定"
                        
                                        cancelButtonBlock:^{
                                        } otherButtonBlock:^(NSString * result){
                                            NSNumber *percent = [NSNumber numberWithInteger:result.integerValue];
                                            [[ServerManager shareServer] startModifiyPushData:self.gameCode PushGame:pushGameCode perent:percent back:^(BOOL success) {
                                                if (success) {
                                                    [self loadServerData]; //加载
                                                }else{
                                                    
                                                }
                                            }];
                                        }];
    [self.stAlertView setNumberPad];
}

- (void)clickMember:(TanTableViewCell *)cell
{
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
